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
 * The ecosystem of a programming language is the elements of a language and the links between them. A compiler, libraries,
 * functions, procedures, classes, instructions, statements, variables and structures all are included in the ecosystem.
 */
@Data
public class Ecosystem {

    /**
     * The platform of the ecosystem. Legal values could be 'jvm', 'node', etc.
     */
    @NotBlank
    String platform;

    /**
     * The flavor (or language) which is executed on the platform. Legal values could be 'java', 'kotlin', 'javascript', etc.
     */
    @NotBlank
    String flavor;

    /**
     * The version of the used ecosystem (if available).
     */
    String version;

    /**
     * An (optional) url to access more information about the framework.
     */
    String url;
}
