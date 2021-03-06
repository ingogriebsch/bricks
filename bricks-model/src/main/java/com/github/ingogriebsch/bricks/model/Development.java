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

import lombok.Data;

/**
 * Development is the process of conceiving, specifying, designing, programming, documenting, testing, and bug fixing involved in
 * creating and maintaining applications, frameworks, or other software components.
 */
@Data
public class Development {

    /**
     * A collection of management systems which are used to manage the development of the application.
     */
    @Valid
    Set<ManagementSystem> managementSystems;

    /**
     * A collection of the main frameworks which are used to implement the component.
     */
    @Valid
    Set<Framework> frameworks;

    /**
     * A collection of the main build tools which are used to build the component.
     */
    @Valid
    Set<BuildTool> buildTools;

}
