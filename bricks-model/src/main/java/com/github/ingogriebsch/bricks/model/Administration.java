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

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Administration {

    /**
     * The type of the administration. Legal values could be 'ui', 'console', etc.
     */
    @NotBlank
    private String type;

    /**
     * The (component relative) path to access the administration (if available).
     */
    private String path;

    /**
     * The type of authorization which is needed to access the administration (if protected). Legal values could be 'oauth2',
     * 'basic', etc.
     */
    private String authorizationType;

}
