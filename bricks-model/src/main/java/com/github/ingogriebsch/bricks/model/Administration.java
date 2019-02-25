package com.github.ingogriebsch.bricks.model;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Administration {

    /**
     * The type of the administration. Legal values could be 'ui', 'console', etc.
     */
    @NotBlank
    private String type;

    /**
     * The (component relative) path to access the administration (if available).
     */
    private String path;

    /**
     * The type of authorization which is needed to access the administration (if protected). Legal values could be 'oauth2',
     * 'basic', etc.
     */
    private String authorizationType;

}
