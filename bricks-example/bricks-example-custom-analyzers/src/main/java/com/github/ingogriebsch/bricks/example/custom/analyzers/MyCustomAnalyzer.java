/*-
 * #%L
 * Example for company specific analyzers
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
package com.github.ingogriebsch.bricks.example.custom.analyzers;

import com.github.ingogriebsch.bricks.maven.plugin.analyzer.AnalysisResult;
import com.github.ingogriebsch.bricks.maven.plugin.analyzer.CustomMavenAnalyzer;
import com.github.ingogriebsch.bricks.model.Component;
import com.github.ingogriebsch.bricks.model.Layer;

public class MyCustomAnalyzer extends CustomMavenAnalyzer {

    @Override
    protected AnalysisResult augment(Component c) {
        log.info("started");

        Layer layer = new Layer();
        layer.setId("layer");
        c.setLayer(layer);

        return AnalysisResult.OK;
    }

}
