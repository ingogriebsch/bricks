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

/**
 * An application is a product or program that can be used by the end user.
 */
@Data
public class Application {

    /**
     * The (unique) id of the application.
     */
    @NotBlank
    String id;

    /**
     * The human readable name of the application.
     */
    @NotBlank
    String name;

    /**
     * An explanation to describe the main purpose of the application.
     */
    @NotBlank
    String description;

    /**
     * The (current) version of the application.
     */
    @NotBlank
    String version;

    /**
     * The license under which the application is published.
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
     * A collection of management systems which are used to manage the application.
     */
    @Valid
    Set<ManagementSystem> managementSystems;

}
