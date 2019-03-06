/*-
 * #%L
 * Bricks Assemble
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
package com.github.ingogriebsch.bricks.assemble.loader.github;

import lombok.Builder;
import lombok.NonNull;

@Builder
public class SimpleResourceOriginProvider implements ResourceOriginProvider {

    static final String DEFAULT_REF = "master";
    static final String DEFAULT_PATH = "bricks.json";

    @Builder.Default
    private String ref = DEFAULT_REF;
    @Builder.Default
    private String path = DEFAULT_PATH;

    @Override
    public ResourceOrigin get(@NonNull String componentId) {
        return new ResourceOrigin(componentId, ref, path);
    }

}
