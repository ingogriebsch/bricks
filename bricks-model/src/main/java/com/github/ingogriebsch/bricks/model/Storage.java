/*-
 * #%L
 * Bricks Model
 * %%
 * Copyright (C) 2018 - 2019 Ingo Griebsch
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.github.ingogriebsch.bricks.model;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * Storage is a technology consisting of computer components and recording media that are used to retain digital data.
 */
@Data
public class Storage {

    /**
     * The (unique) id of the storage.
     */
    @NotBlank
    String id;

    /**
     * The type of the storage. Legal values could be 'database', 'file-system', etc.
     */
    @NotBlank
    String type;

    /**
     * The vendor of the storage.
     */
    @NotBlank
    String vendor;

    /**
     * Specifies if the storage is persistent.
     */
    boolean persistent;

}
