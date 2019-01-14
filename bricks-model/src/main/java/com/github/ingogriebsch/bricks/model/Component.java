package com.github.ingogriebsch.bricks.model;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Component {

    /**
     * The (unique) id of the component.
     */
    @NotBlank
    private String id;

    /**
     * The human readable name of the component.
     */
    @NotBlank
    private String name;

    /**
     * An explanation to describe the main purpose of the component.
     */
    @NotBlank
    private String description;

    /**
     * The (current) version of the component.
     */
    @NotBlank
    @Pattern(regexp = "(\\d+)\\.(\\d+)(?:\\.)?(\\d*)(\\.|-|\\+)?([0-9A-Za-z-.]*)?")
    private String version;

    /**
     * Explains in which layer the component is positioned inside the whole application. Legal values could be 'edge',
     * 'integration', 'core', etc.
     */
    @NotBlank
    private String layer;

    /**
     * A collection of categories the component is related to. Legal values could be 'functional', 'infrastructure', 'bff',
     * 'apigateway', 'storage', etc.
     */
    @Valid
    private Set<String> categories;

    /**
     * Specifies if the component is failover capable.
     */
    private boolean failoverCapable;

    /**
     * Specifies if the component is horizontal scalable.
     */
    private boolean horizontalScalable;

    /**
     * A collection of the main frameworks which are used to implement the component.
     */
    @NotEmpty
    private Set<String> frameworks;

    /**
     * A collection of the main build tools which are used to build the component.
     */
    @NotEmpty
    private Set<String> buildTools;

    /**
     * A collection of the main ecosystems the component is implemented in.
     */
    @NotEmpty
    @Valid
    private Set<Ecosystem> ecosystems;

    /**
     * A collection of the storages the component is using.
     */
    @Valid
    private Set<Storage> storages;

    /**
     * Describes the memory footprint of the component.
     */
    @NotNull
    @Valid
    private MemoryFootprint memoryFootprint;

    /**
     * Describes the administration possibilities of the component.
     */
    @Valid
    private Administration administration;

    /**
     * A collection of persons or teams which are responsible for the component.
     */
    @NotEmpty
    @Valid
    private Set<Responsible> responsibles;

    /**
     * Describes the communication capabilities of the component.
     */
    @Valid
    private Communication communication;

    /**
     * Describes the monitoring capabilities of the component.
     */
    @Valid
    private Monitoring monitoring;
}
