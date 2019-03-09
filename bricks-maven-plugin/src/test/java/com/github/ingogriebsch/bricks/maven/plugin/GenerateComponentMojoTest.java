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
package com.github.ingogriebsch.bricks.maven.plugin;

import static org.assertj.core.api.Assertions.*;

import java.io.File;

import com.github.ingogriebsch.bricks.model.Component;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class GenerateComponentMojoTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    // @Test
    // public void execute_should_create_file() throws Exception {
    // File outputDirectory = temporaryFolder.getRoot();
    // String outputFilename = "bricks.json";
    //
    // Component component = new Component();
    // component.setId("id");
    //
    // GenerateComponentMojo mojo = new GenerateComponentMojo();
    // mojo.setOutputDirectory(outputDirectory);
    // mojo.setOutputFilename(outputFilename);
    // mojo.setComponent(component);
    // mojo.execute();
    //
    // assertThat(new File(outputDirectory, outputFilename)).exists().isFile();
    // }

}
