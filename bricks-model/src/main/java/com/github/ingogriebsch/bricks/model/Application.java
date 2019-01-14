package com.github.ingogriebsch.bricks.model;

import java.util.Set;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Application {

    /**
     * The (unique) id of the application.
     */
    @NotBlank
    private String id;

    /**
     * The human readable name of the application.
     */
    @NotBlank
    private String name;

    /**
     * An explanation to describe the main purpose of the application.
     */
    @NotBlank
    private String description;

    /**
     * The (current) version of the application.
     */
    @NotBlank
    private String version;

    /**
     * A collection of components that make up the application.
     */
    @NotEmpty
    @Valid
    private Set<Component> components;

}
