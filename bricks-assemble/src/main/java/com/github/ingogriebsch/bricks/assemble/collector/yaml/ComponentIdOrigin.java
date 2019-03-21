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
package com.github.ingogriebsch.bricks.assemble.collector.yaml;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Wither;

@Builder
@Value
@Wither
public class ComponentIdOrigin {

    @Builder.Default
    @NonNull
    String key = "id";

    @Builder.Default
    @NonNull
    String[] parents = new String[] { "components" };

    @Builder.Default
    @NonNull
    ApplicationIdOrigin applicationIdOrigin = ApplicationIdOrigin.defaultOrigin();

    public static ComponentIdOrigin defaultOrigin() {
        return ComponentIdOrigin.builder().build();
    }
}
