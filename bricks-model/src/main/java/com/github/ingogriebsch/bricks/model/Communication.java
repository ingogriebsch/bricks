package com.github.ingogriebsch.bricks.model;

import java.util.Set;

import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Communication {

    /**
     * A collection of interfaces the component provides.
     */
    @Valid
    private Set<Interface> interfaces;

    /**
     * Describes if the component acts as a source of messages.
     */
    @Valid
    private Messaging messaging;

    /**
     * The dependency (namely component) this component is communicating with.
     */
    @Valid
    private Set<Dependency> dependencies;

}
