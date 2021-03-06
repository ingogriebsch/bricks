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
 * A framework is an abstraction in which software providing generic functionality can be selectively changed by additional
 * user-written code, thus providing application-specific software.
 */
@Data
public class Framework {

    /**
     * The name of the framework. Possible values could be 'Spring', 'Hibernate', 'JBPM', etc.
     */
    @NotBlank
    String name;

    /**
     * The (optional) version of the framework.
     */
    String version;

    /**
     * An (optional) url to access more information about the framework.
     */
    String url;
}
