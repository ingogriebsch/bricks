package com.github.ingogriebsch.bricks.maven.plugin;

import static java.lang.String.format;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import static com.fasterxml.jackson.databind.SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_NULL_MAP_VALUES;
import static com.google.common.io.Files.createParentDirs;
import static lombok.AccessLevel.PACKAGE;
import static org.apache.maven.plugins.annotations.LifecyclePhase.PROCESS_RESOURCES;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ingogriebsch.bricks.model.Component;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

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
