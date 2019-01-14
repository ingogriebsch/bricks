package com.github.ingogriebsch.bricks.model;

import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Monitoring {

    /**
     * Describes which metrics the component provides.
     */
    @Valid
    private Metrics metrics;

    /**
     * Describes how the component logs about it's internal state.
     */
    @Valid
    private Logging logging;
}
