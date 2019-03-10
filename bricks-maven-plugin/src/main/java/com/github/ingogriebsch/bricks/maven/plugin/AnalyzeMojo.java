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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import com.github.ingogriebsch.bricks.maven.plugin.analyzer.AnalyzerContext;
import com.github.ingogriebsch.bricks.maven.plugin.analyzer.discovery.Analyzers;
import com.github.ingogriebsch.bricks.model.Component;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import lombok.SneakyThrows;

/**
 * Goal that analyzes a Project using a bunch of discovered Analyzers
 * 
 */
@Mojo(name = "analyze", threadSafe = false)
public class AnalyzeMojo extends AbstractMojo {

    @Parameter(defaultValue = "META-INF/bricks.json")
    private String outputFilename;

    @Parameter(property = "stdout")
    private boolean stdout = false;

    private final ObjectMapperFactory omf = new ObjectMapperFactory();

    @Parameter(defaultValue = "${session}", readonly = true)
    private MavenSession mavenSession;

    @Override
    @SneakyThrows
    public void execute() throws MojoExecutionException, MojoFailureException {
        try (AnalyzerContext ctx = AnalyzerContext.of(mavenSession, getLog())) {
            Component c = new Component();
            new Analyzers(ctx).augment(c);
            write(renderComponent(c));
        }
    }

    private void write(String json) throws MojoExecutionException {
        if (stdout) {
            getLog().info("");
            getLog().info("Output: \n" + json);
        } else {
            try (PrintStream pw = createWriter()) {
                pw.println(json);
            } catch (IOException e) {
                throw new MojoExecutionException("Failed to execute Plugin", e);
            }
        }
    }

    @SneakyThrows
    private String renderComponent(Component c) {
        // TODO ingo am i right to assume the model has to be validated before
        // persisting?
        return omf.get().writeValueAsString(c);
    }

    private PrintStream createWriter() throws FileNotFoundException {
        File f = new File(mavenSession.getCurrentProject().getBuild().getOutputDirectory(), outputFilename);
        f.getParentFile().mkdirs();
        return new PrintStream(new BufferedOutputStream(new FileOutputStream(f)));
    }

}
