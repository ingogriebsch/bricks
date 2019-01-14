package com.github.ingogriebsch.bricks.model;

import java.util.Set;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Dependency {

    /**
     * The id of the component this component depends on.
     */
    @NotBlank
    private String target;

    /**
     * A collection of interfaces the component depends on.
     */
    @Valid
    private Set<Interface> interfaces;

    /**
     * Describes if the component is a target for messages.
     */
    @Valid
    private Messaging messaging;
}
