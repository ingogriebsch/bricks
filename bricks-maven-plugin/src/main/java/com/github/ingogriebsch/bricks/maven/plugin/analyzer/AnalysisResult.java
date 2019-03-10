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

public enum AnalysisResult {
        // successful
        OK('\u2713'),

        // not successful due to missing information
        SKIPPED('.'),

        // not successful due to faulty/incomplete information,
        // should be fixed, but we can continue
        //
        // same as throwing an Exception from augment(Component)
        FAIL('\u2718'),

        // something is very broken here. we need to abort analysis.
        //
        // same as throwing an MavenExecutionException or
        // MojoExecutionException from augment(Component)
        FATAL('\u271d');

    private char c;

    private AnalysisResult(char c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return String.valueOf(c);
    }
}
