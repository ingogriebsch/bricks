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
public class Interface {

    /**
     * The (component relative) base path to access the interface.
     */
    @NotBlank
    private String basePath;

    /**
     * The communication protocol which needs to be used to access the interface.
     */
    @NotBlank
    private String protocol;

    /**
     * The technology in which the interface is implemented. Legal values could be 'REST', 'gRPC', 'GraphQL', etc.
     */
    @NotBlank
    private String technology;

    /**
     * The underlying provider which allows access to the interface.
     */
    @NotBlank
    private String provider;

    /**
     * The type of authorization which is needed to access the interface (if protected). Legal values could be 'oauth2', 'basic',
     * etc.
     */
    private String authorizationType;

    /**
     * A collection of endpoints the interface provides.
     */
    @Valid
    private Set<Endpoint> endpoints;
}
