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
package com.github.ingogriebsch.bricks.maven.plugin.analyzer.standard;

import com.github.ingogriebsch.bricks.maven.plugin.analyzer.AbstractMavenAnalyzer;
import com.github.ingogriebsch.bricks.maven.plugin.analyzer.AnalysisResult;
import com.github.ingogriebsch.bricks.model.Component;
import com.github.ingogriebsch.bricks.model.MavenCoordinates;

import org.apache.maven.project.MavenProject;

public class BasicProjectAnalyzer extends AbstractMavenAnalyzer {

    @Override
    protected AnalysisResult augment(Component c) {

        c.setName(project.getName());
        c.setDescription(project.getDescription());

        c.setMavenCoordinates(fromCoordinates(project));

        // this would likely be obsolete then.
        c.setVersion(project.getVersion());

        return AnalysisResult.OK;
    }

    private MavenCoordinates fromCoordinates(MavenProject project) {

        MavenCoordinates c = new MavenCoordinates();
        c.setGroupId(project.getGroupId());
        c.setArtifactId(project.getArtifactId());
        c.setVersion(project.getVersion());

        return c;
    }

}
