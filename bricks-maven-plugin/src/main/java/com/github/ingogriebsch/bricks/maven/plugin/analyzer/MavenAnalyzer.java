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
package com.github.ingogriebsch.bricks.maven.plugin.analyzer;

import java.util.Comparator;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.github.ingogriebsch.bricks.model.Component;

import lombok.NonNull;

/**
 * Main entrypoint for an analyzer to do its magic and contribute generated
 * insight to the Component given.
 * 
 * To do it "right" rather than using ordinals, we could build a dependency
 * graph of analyzers and even run them in parallel. Imho not worth it right
 * now, because plugin is only supposed to run on package phase.
 * 
 * @author doc
 *
 */
public interface MavenAnalyzer {

    static int DEFAULT_ORDINAL = 1000;

    public static final Comparator<MavenAnalyzer> BY_ORDINAL = //
        (o1, o2) -> {
            int ret = o1.ordinal() < o2.ordinal() ? -1 : 1;

            if (ret == 0)
                // in order to get a stable order in case of equal ordinals
                return o1.getClass().getSimpleName().compareTo(o2.getClass().getSimpleName());
            else
                return ret;
        };

    default int ordinal() {
        return DEFAULT_ORDINAL;
    }

    @NonNull
    AnalysisResult augment(@NonNull AnalyzerContext ctx, @NonNull Component c)
        throws MojoExecutionException, MojoFailureException, Exception;
}
