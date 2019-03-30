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

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;

import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import com.github.ingogriebsch.bricks.analyze.ComponentsPerLayerAnalyzer.ComponentsPerLayer;
import com.github.ingogriebsch.bricks.model.Component;
import com.github.ingogriebsch.bricks.model.Layer;

import org.junit.jupiter.api.Test;

public class ComponentsPerLayerAnalyzerTest {

    @Test
    public void analyzer_should_throw_exception_if_components_is_null() {
        assertThrows(NullPointerException.class, () -> ComponentsPerLayerAnalyzer.builder().components(null).build());
    }

    @Test
    public void analyzer_should_throw_exception_if_no_components_given() {
        assertThrows(NullPointerException.class, () -> ComponentsPerLayerAnalyzer.builder().build());
    }

    @Test
    public void analyzer_should_return_matching_result_if_one_component_with_one_layer_is_given() {
        Layer layer = layer("layer");
        Component component = component(layer);

        ComponentsPerLayerAnalyzer builder = ComponentsPerLayerAnalyzer.builder().components(newHashSet(component)).build();

        Set<ComponentsPerLayer> componentsPerLayers = builder.analyze();
        assertThat(componentsPerLayers).isNotNull().hasSize(1);

        ComponentsPerLayer componentsPerLayer = componentsPerLayers.iterator().next();
        assertThat(componentsPerLayer).isNotNull();
        assertThat(componentsPerLayer).hasFieldOrPropertyWithValue("layer", layer);

        Set<Component> components = componentsPerLayer.getComponents();
        assertThat(components).isNotNull().containsExactly(component);
    }

    @Test
    public void analyzer_should_return_matching_result_if_several_components_with_one_layer_is_given() {
        Layer layer = layer("layer");
        Set<Component> components = newHashSet(component(layer), component(layer), component(layer));

        ComponentsPerLayerAnalyzer builder = ComponentsPerLayerAnalyzer.builder().components(components).build();

        Set<ComponentsPerLayer> componentsPerLayers = builder.analyze();
        assertThat(componentsPerLayers).isNotNull().hasSize(1);

        ComponentsPerLayer componentsPerLayer = componentsPerLayers.iterator().next();
        assertThat(componentsPerLayer).isNotNull();
        assertThat(componentsPerLayer).hasFieldOrPropertyWithValue("layer", layer);

        assertThat(componentsPerLayer.getComponents()).isNotNull()
            .containsExactlyInAnyOrder(components.toArray(new Component[components.size()]));
    }

    @Test
    public void analyzer_should_return_matching_result_if_serveral_layers_with_one_component_each_is_given() {
        Set<Component> components =
            newHashSet(component(layer("layer1")), component(layer("layer2")), component(layer("layer3")));
        Set<ComponentsPerLayer> componentsPerLayers =
            components.stream().map(c -> componentsPerLayer(c.getLayer(), c)).collect(toSet());

        ComponentsPerLayerAnalyzer builder = ComponentsPerLayerAnalyzer.builder().components(components).build();
        assertThat(builder.analyze()).isNotNull().hasSize(3).containsExactlyInAnyOrderElementsOf(componentsPerLayers);
    }

    @Test
    public void componentsPerLayer_should_represent_matching_layer() {
        String layerId = "layer";
        Layer layer = layer(layerId);
        Component component = component(layer);
        ComponentsPerLayer componentsPerLayer = new ComponentsPerLayer(layer, newHashSet(component));

        assertThat(componentsPerLayer).isNotNull();
        assertThat(componentsPerLayer.represents(null)).isFalse();
        assertThat(componentsPerLayer.represents(layer(layerId + "123"))).isFalse();
        assertThat(componentsPerLayer.represents(layer(layerId))).isTrue();
        assertThat(componentsPerLayer.represents(layer)).isTrue();
    }

    private static ComponentsPerLayer componentsPerLayer(Layer layer, Component... components) {
        return new ComponentsPerLayer(layer, newHashSet(asList(components)));
    }

    private static Layer layer(String layerId) {
        Layer layer = new Layer();
        layer.setId(layerId);
        return layer;
    }

    private static Component component(Layer layer) {
        Component component = new Component();
        component.setLayer(layer);
        return component;
    }
}
