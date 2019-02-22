package com.github.ingogriebsch.bricks.maven.plugin;

import static java.lang.String.format;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "generate-component", defaultPhase = LifecyclePhase.PROCESS_RESOURCES)
public class GenerateComponentMojo extends AbstractMojo {

    @Parameter(required = true, defaultValue = "bricks.json")
    private String outputFilename;

    @Parameter(required = true, defaultValue = "${project.build.directory}")
    private File outputDirectory;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Executing generate-component...");

        if (!outputDirectory.exists()) {
            if (!outputDirectory.mkdirs()) {
                throw new MojoExecutionException("Cannot create output directoy!");
            }
            getLog().info(format("Creating folder structure for '%s'...", outputDirectory.getAbsolutePath()));
        }

        File outputFile = new File(outputDirectory, outputFilename);

        try {
            if (!outputFile.createNewFile()) {
                throw new MojoExecutionException("Cannot create new output file!");
            }
            getLog().info(format("Creating file '%s'...", outputFile.getAbsolutePath()));
        } catch (IOException e) {
            throw new MojoExecutionException("Cannot create new output file!");
        }

        getLog().info("Executing generate-component done!");
    }

}
