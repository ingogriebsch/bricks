package com.github.ingogriebsch.bricks.maven.plugin;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import com.github.ingogriebsch.bricks.model.Component;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class GenerateComponentMojoTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void execute_should_create_file() throws Exception {
        File outputDirectory = temporaryFolder.getRoot();
        String outputFilename = "bricks.json";

        Component component = new Component();
        component.setId("id");

        GenerateComponentMojo mojo = new GenerateComponentMojo();
        mojo.setOutputDirectory(outputDirectory);
        mojo.setOutputFilename(outputFilename);
        mojo.setComponent(component);
        mojo.execute();

        assertThat(new File(outputDirectory, outputFilename)).exists().isFile();
    }

}
