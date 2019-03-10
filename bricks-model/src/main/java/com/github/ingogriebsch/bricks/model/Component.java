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

import lombok.Data;

@Data
public class Component {

    /**
     * The (unique) id of the component.
     */
    @NotBlank
    String id;

    /**
     * The human readable name of the component.
     */
    @NotBlank
    String name;

    /**
     * An explanation to describe the main purpose of the component.
     */
    @NotBlank
    String description;

    /**
     * The (current) version of the component.
     */
    @NotBlank
    String version;

    /**
     * The license under which the component is published.
     */
    @Valid
    License license;

    /**
     * A collection of persons or teams which are responsible for the component.
     */
    @NotEmpty
    @Valid
    Set<Responsible> responsibles;

    /**
     * Explains in which layer the component is positioned inside the whole application. Legal values could be 'edge',
     * 'integration', 'core', etc.
     */
    String layer;

    /**
     * A collection of categories the component is related to. Legal values could be 'functional', 'infrastructure', 'bff',
     * 'apigateway', 'storage', etc.
     */
    Set<String> categories;

    /**
     * A collection of the main ecosystems the component is implemented in.
     */
    @NotEmpty
    @Valid
    Set<Ecosystem> ecosystems;

    /**
     * A section to hold information about the development of the component.
     */
    @Valid
    Development development;

    /**
     * A section to hold information about how to handle the runtime of the component.
     */
    // FIXME Is this section really necessary? All (at least most of) the information will heavily depend on a specific
    // environment in which the component is executed.
    @Valid
    Runtime runtime;

    /**
     * A collection of the storages the component is using.
     */
    @Valid
    Set<Storage> storages;

    /**
     * Describes the communication capabilities of the component.
     */
    @Valid
    Communication communication;

}
