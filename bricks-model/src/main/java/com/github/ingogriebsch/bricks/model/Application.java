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

import java.util.Set;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Application {

    /**
     * The (unique) id of the application.
     */
    @NotBlank
    private String id;

    /**
     * The human readable name of the application.
     */
    @NotBlank
    private String name;

    /**
     * An explanation to describe the main purpose of the application.
     */
    @NotBlank
    private String description;

    /**
     * The (current) version of the application.
     */
    @NotBlank
    private String version;

    /**
     * The license under which the application is published.
     */
    @Valid
    private License license;

    /**
     * A collection of persons or teams which are responsible for the component.
     */
    @NotEmpty
    @Valid
    private Set<Responsible> responsibles;

    /**
     * A collection of management systems which are used to manage the application.
     */
    @Valid
    private Set<ManagementSystem> managementSystems;

    /**
     * A collection of components that make up the application.
     */
    // FIXME Should this really be part of the application? If we couple the components in a loose way, we would not have the need
    // to use an assembler and can be more flexible in implementing different loaders/converters/readers.
    @Valid
    private Set<Component> components;

}
