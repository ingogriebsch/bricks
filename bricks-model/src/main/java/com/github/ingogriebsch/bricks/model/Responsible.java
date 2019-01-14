package com.github.ingogriebsch.bricks.model;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Responsible {

    /**
     * The (human readable) name of the responsible person or team.
     */
    @NotBlank
    private String name;

    /**
     * The email address of the responsible person or team.
     */
    @NotBlank
    private String email;
}
