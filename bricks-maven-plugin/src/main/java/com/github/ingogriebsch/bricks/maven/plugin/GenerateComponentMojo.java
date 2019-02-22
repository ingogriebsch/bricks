package com.github.ingogriebsch.bricks.maven.plugin;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
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

@Mojo(name = "generate-component", defaultPhase = PROCESS_RESOURCES)
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
        objectMapper.enable(INDENT_OUTPUT);
        return objectMapper;
    }

}
