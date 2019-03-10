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

@Data
public class ManagementSystem {

    /**
     * The type of the management system. Legal values could be 'continuous integration', 'issue tracker', 'continuous
     * inspection', 'source control', etc.
     */
    @NotBlank
    String type;

    /**
     * The name of the mangement system. Possible values could be 'GitHub', 'Jenkins', 'Jira', 'Sonarcube', etc.
     */
    @NotBlank
    String name;

    /**
     * An (optional) url to access the management system.
     */
    String url;
}
