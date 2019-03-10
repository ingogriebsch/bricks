/*-
 * #%L
 * Bricks Maven Plugin
 * %%
 * Copyright (C) 2018 - 2019 Ingo Griebsch
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.github.ingogriebsch.bricks.maven.plugin.analyzer.discovery;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.github.ingogriebsch.bricks.maven.plugin.analyzer.AnalysisResult;
import com.github.ingogriebsch.bricks.maven.plugin.analyzer.AnalyzerContext;
import com.github.ingogriebsch.bricks.maven.plugin.analyzer.MavenAnalyzer;
import com.github.ingogriebsch.bricks.model.Component;
import com.google.common.base.Stopwatch;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * Iterates and applies Analyzers and logs results
 * 
 */
@RequiredArgsConstructor
public final class Analyzers {

    private final AnalyzerContext ctx;

    private final AnalyzerDiscovery discovery;

    public Analyzers(@NonNull AnalyzerContext ctx) {
        this(ctx, new ReflectionsAnalyzerDiscovery());
    }

    @SneakyThrows
    public synchronized void augment(@NonNull Component c) throws AbstractMojoExecutionException {
        Collection<MavenAnalyzer> analyzers = discovery.findEnabledAnalyzers(ctx);
        ctx.log().info("Running " + analyzers.size() + " Analyzers");
        ctx.log().info("");
        analyzers.stream().sorted(MavenAnalyzer.BY_ORDINAL).forEach(a -> run(a, c));
    }

    @SneakyThrows
    private void run(MavenAnalyzer analyzer, Component component) {
        Stopwatch sw = Stopwatch.createStarted();
        AnalysisResult result = null;
        try {

            String displayName = displayName(analyzer);
            AnalyzerContext childContext = ctx.childContext(displayName);

            result = analyzer.augment(childContext, component);

            if (result == null) {
                throw new MojoFailureException(displayName + " returned null. This is not allowed.");
            }

            ctx.log().info(renderAnalysisResult(result, analyzer, sw));

        } catch (AbstractMojoExecutionException e) {
            logFailure(AnalysisResult.FATAL, analyzer, sw, e);
            throw e;
        } catch (Exception anythingElse) {
            logFailure(AnalysisResult.FAIL, analyzer, sw, anythingElse);
        }

        if (result == AnalysisResult.FATAL) {
            throw new MojoFailureException("Analyzer " + displayName(analyzer) + " had a FATAL result -> stopping");
        }

    }

    private String displayName(@NonNull MavenAnalyzer analyzer) {
        return analyzer.getClass().getSimpleName();
    }

    private void logFailure(AnalysisResult result, MavenAnalyzer analyzer, Stopwatch sw, Exception ex) {
        // we intentionally do not fail or warn here, as the failure of
        // single analyzers is acceptable
        ctx.log().info(renderAnalysisResult(result, analyzer, sw));
        // use -X to see what is going on, if this is unexpected
        ctx.log().debug(ex.getMessage(), ex);
    }

    private String renderAnalysisResult(AnalysisResult result, MavenAnalyzer a, Stopwatch sw) {
        return StringUtils.rightPad(result + " " + a.getClass().getSimpleName() + " done ", 63) + millis(sw);
    }

    private String millis(Stopwatch sw) {
        return StringUtils.leftPad(String.valueOf(sw.stop().elapsed(TimeUnit.MILLISECONDS)), 6) + " ms";
    }

}
