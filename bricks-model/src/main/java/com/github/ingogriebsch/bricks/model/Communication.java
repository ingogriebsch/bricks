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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Communication {

    /**
     * A collection of interfaces the component provides.
     */
    @Valid
    private Set<Interface> interfaces;

    /**
     * Describes if the component acts as a source of messages.
     */
    @Valid
    private Messaging messaging;

    /**
     * The dependency (namely component) this component is communicating with.
     */
    @Valid
    private Set<Dependency> dependencies;

}
