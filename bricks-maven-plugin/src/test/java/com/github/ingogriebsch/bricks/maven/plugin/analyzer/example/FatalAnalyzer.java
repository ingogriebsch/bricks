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
package com.github.ingogriebsch.bricks.maven.plugin.analyzer.example;

import com.github.ingogriebsch.bricks.maven.plugin.analyzer.AnalysisResult;
import com.github.ingogriebsch.bricks.maven.plugin.analyzer.AnalyzerContext;
import com.github.ingogriebsch.bricks.maven.plugin.analyzer.MavenAnalyzer;
import com.github.ingogriebsch.bricks.model.Component;

import lombok.SneakyThrows;

public class FatalAnalyzer implements MavenAnalyzer {

    @Override
    @SneakyThrows
    public AnalysisResult augment(AnalyzerContext ctx, Component c) {
        return AnalysisResult.FATAL;
    }

    @Override
    public int ordinal() {
        return 99999999;
    }
}
