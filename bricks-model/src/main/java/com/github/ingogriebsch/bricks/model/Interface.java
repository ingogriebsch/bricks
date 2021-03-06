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

import lombok.Data;

/**
 * An interface is a shared boundary across which two or more separate components of an application exchange information.
 */
@Data
public class Interface {

    /**
     * The (component relative) base path to access the interface.
     */
    @NotBlank
    String basePath;

    /**
     * The communication protocol which needs to be used to access the interface.
     */
    @NotBlank
    String protocol;

    /**
     * The technology in which the interface is implemented. Legal values could be 'REST', 'gRPC', 'GraphQL', etc.
     */
    @NotBlank
    String technology;

    /**
     * The underlying provider which allows access to the interface.
     */
    @NotBlank
    String provider;

    /**
     * The type of authorization which is needed to access the interface (if protected). Legal values could be 'oauth2', 'basic',
     * etc.
     */
    String authorizationType;

    /**
     * A collection of endpoints the interface provides.
     */
    @Valid
    Set<Endpoint> endpoints;
}
