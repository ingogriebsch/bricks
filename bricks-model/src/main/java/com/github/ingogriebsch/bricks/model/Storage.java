package com.github.ingogriebsch.bricks.model;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Storage {

    /**
     * The (unique) id of the storage.
     */
    @NotBlank
    private String id;

    /**
     * The type of the storage. Legal values could be 'database', 'file-system', etc.
     */
    @NotBlank
    private String type;

    /**
     * The vendor of the storage.
     */
    @NotBlank
    private String vendor;

    /**
     * Specifies if the storage is persistent.
     */
    private boolean persistent;

}
