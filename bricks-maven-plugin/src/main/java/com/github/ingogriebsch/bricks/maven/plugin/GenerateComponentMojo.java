/*
 * Copyright © 2018 Ingo Griebsch (https://ingogriebsch.de/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.ingogriebsch.bricks.maven.plugin;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import static com.fasterxml.jackson.databind.SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_NULL_MAP_VALUES;
import static com.google.common.io.Files.createParentDirs;
import static java.lang.String.format;
import static lombok.AccessLevel.PACKAGE;
import static org.apache.maven.plugins.annotations.LifecyclePhase.PROCESS_RESOURCES;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ingogriebsch.bricks.model.Component;

import lombok.Setter;

@Mojo(name = "generate-component", defaultPhase = PROCESS_RESOURCES, threadSafe = false)
@Setter(PACKAGE)
public class GenerateComponentMojo extends AbstractMojo {

    @Parameter(required = true, defaultValue = "bricks.json")
    private String outputFilename;

    @Parameter(required = true, defaultValue = "${project.basedir}")
    private File outputDirectory;

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject mavenProject;

    @Parameter(required = true)
    private Component component;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        File outputFile = new File(outputDirectory, outputFilename);

        try {
            createParentDirs(outputFile);
        } catch (IOException e) {
            throw new MojoExecutionException(format("Cannot create output directoy '%s'!", outputFile.getParentFile()));
        }

        ObjectMapper objectMapper = createAndPrepareObjectMapper();
        try {
            objectMapper.writeValue(outputFile, component);
        } catch (IOException e) {
            throw new MojoExecutionException(String.format("Cannot write component to '%s'!", outputFile));
        }
    }

    private ObjectMapper createAndPrepareObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(NON_NULL);
        objectMapper.disable(WRITE_NULL_MAP_VALUES);
        objectMapper.enable(ORDER_MAP_ENTRIES_BY_KEYS);
        objectMapper.enable(INDENT_OUTPUT);
        return objectMapper;
    }

}
