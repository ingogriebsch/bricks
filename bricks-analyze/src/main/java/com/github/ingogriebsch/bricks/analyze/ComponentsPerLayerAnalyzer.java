/*-
 * #%L
 * Bricks Analyze
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
package com.github.ingogriebsch.bricks.analyze;

import static java.util.stream.Collectors.groupingBy;

import static com.google.common.collect.Sets.newHashSet;

import java.util.Set;

import com.github.ingogriebsch.bricks.model.Component;
import com.github.ingogriebsch.bricks.model.Layer;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder
public class ComponentsPerLayerAnalyzer {

    @NonNull
    private Set<Component> components;

    public Set<ComponentsPerLayer> analyze() {
        Set<ComponentsPerLayer> componentsPerLayers = newHashSet();
        components.stream().collect(groupingBy(Component::getLayer))
            .forEach((k, v) -> componentsPerLayers.add(new ComponentsPerLayer(k, newHashSet(v))));
        return componentsPerLayers;
    }

    @Value
    public static class ComponentsPerLayer {

        @NonNull
        Layer layer;
        @NonNull
        Set<Component> components;

        public boolean represents(Layer layer) {
            return this.getLayer().equals(layer);
        }

    }
}
