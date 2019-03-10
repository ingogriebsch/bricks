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

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.reflections.Reflections;

import com.github.ingogriebsch.bricks.model.Component;

import lombok.NonNull;

/**
 * Optional convenience superclass that initializes fields from the context, so
 * that subclasses can be less verbose.
 * 
 * 
 * @author doc
 *
 */
public abstract class AbstractMavenAnalyzer implements MavenAnalyzer {

    protected Object session;

    protected MavenProject project;

    protected Log log;

    protected AnalyzerContext ctx;

    protected Reflections reflections;

    @Override
    public final AnalysisResult augment(@NonNull AnalyzerContext ctx, @NonNull Component c) throws Exception {
        this.ctx = ctx;
        this.log = ctx.log();
        this.project = ctx.project();
        this.session = ctx.session();
        this.reflections = ctx.reflections();

        return augment(c);
    }

    protected abstract @NonNull AnalysisResult augment(@NonNull Component c);
}
