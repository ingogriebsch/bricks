package com.github.ingogriebsch.bricks.model;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Ecosystem {

    /**
     * The platform of the ecosystem. Legal values could be 'jvm', 'node', etc.
     */
    @NotBlank
    private String platform;

    /**
     * The flavor (or language) which is executed on the platform. Legal values could be 'java', 'kotlin', 'javascript', etc.
     */
    @NotBlank
    private String flavor;

    /**
     * The version of the used ecosystem (if available).
     */
    private String version;
}
