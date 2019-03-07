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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A build tool is a programs that automates the creation of executable applications from source code. Building incorporates
 * compiling, linking and packaging the code into a usable or executable form.
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class BuildTool {

    /**
     * The name of the build tool. Possible values could be 'Maven', 'Gradle', 'npm', etc.
     */
    @NotBlank
    private String name;

    /**
     * The (optional) version of the build tool.
     */
    private String version;

    /**
     * An (optional) url to access more information about the framework.
     */
    private String url;
}
