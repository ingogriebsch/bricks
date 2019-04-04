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

import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import com.github.ingogriebsch.bricks.model.Communication;
import com.github.ingogriebsch.bricks.model.Component;
import com.github.ingogriebsch.bricks.model.Dependency;

import org.junit.jupiter.api.Test;

public class ComponentUtilsTest {

    @Test
    public void byId_should_throw_exception_if_id_is_null() {
        assertThrows(NullPointerException.class, () -> ComponentUtils.byId(null, newHashSet()));
    }

    @Test
    public void byId_should_throw_exception_if_components_is_null() {
        assertThrows(NullPointerException.class, () -> ComponentUtils.byId("id", null));
    }

    @Test
    public void byId_should_return_null_if_components_is_empty() {
        assertThat(ComponentUtils.byId("id", newHashSet())).isNull();
    }

    @Test
    public void byId_should_return_null_if_component_is_not_available() {
        assertThat(ComponentUtils.byId("id4", newHashSet(component("id1"), component("id2"), component("id3")))).isNull();
    }

    @Test
    public void byId_should_return_matching_component_if_component_is_available() {
        Component component = component("id2");
        assertThat(ComponentUtils.byId(component.getId(), newHashSet(component("id1"), component, component("id3"))))
            .isEqualTo(component);
    }

    @Test
    public void merge_should_throw_exception_if_one_is_null() {
        assertThrows(NullPointerException.class, () -> ComponentUtils.merge(null, newHashSet()));
    }

    @Test
    public void merge_should_throw_exception_if_two_is_null() {
        assertThrows(NullPointerException.class, () -> ComponentUtils.merge(newHashSet(), null));
    }

    @Test
    public void merge_should_return_empty_result_if_input_is_empty() {
        assertThat(ComponentUtils.merge(newHashSet(), newHashSet())).isEmpty();
    }

    @Test
    public void merge_should_return_one_as_result_if_one_contains_elements_and_two_is_empty() {
        Set<Component> components = newHashSet(component("id1"), component("id2"));
        assertThat(ComponentUtils.merge(components, newHashSet())).isEqualTo(components);
    }

    @Test
    public void merge_should_return_two_as_result_if_two_contains_elements_and_one_is_empty() {
        Set<Component> components = newHashSet(component("id1"), component("id2"));
        assertThat(ComponentUtils.merge(newHashSet(), components)).isEqualTo(components);
    }

    @Test
    public void merge_should_return_matching_result_if_input_contains_the_same_components() {
        Set<Component> one = newHashSet(component("id1"), component("id2"));
        assertThat(ComponentUtils.merge(one, one)).isEqualTo(one);
    }

    @Test
    public void merge_should_return_matching_result_if_input_contains_different_components() {
        Set<Component> result = newHashSet(component("id1"), component("id2"), component("id3"), component("id4"));
        Set<Component> one = newHashSet(component("id1"), component("id2"));
        Set<Component> two = newHashSet(component("id3"), component("id4"));

        assertThat(ComponentUtils.merge(one, two)).isEqualTo(result);
    }

    @Test
    public void merge_should_return_matching_result_if_input_contains_overlapping_components() {
        Set<Component> result = newHashSet(component("id1"), component("id2"), component("id3"), component("id4"));
        Set<Component> one = newHashSet(component("id1"), component("id2"), component("id3"));
        Set<Component> two = newHashSet(component("id2"), component("id3"), component("id4"));

        assertThat(ComponentUtils.merge(one, two)).isEqualTo(result);
    }

    @Test
    public void dependencies_should_return_default_value_if_communication_is_null() {
        Set<Dependency> defaultValue = null;
        Component component = component("id");
        component.setCommunication(null);

        assertThat(ComponentUtils.dependencies(component, defaultValue)).isEqualTo(defaultValue);
    }

    @Test
    public void dependencies_should_return_default_value_if_dependencies_is_null() {
        Set<Dependency> defaultValue = null;
        Communication communication = new Communication();
        communication.setDependencies(null);
        Component component = component("id");
        component.setCommunication(communication);

        assertThat(ComponentUtils.dependencies(component, defaultValue)).isEqualTo(defaultValue);
    }

    @Test
    public void dependencies_should_return_dependencies_if_dependencies_is_empty() {
        Set<Dependency> dependencies = newHashSet();
        Communication communication = new Communication();
        communication.setDependencies(dependencies);
        Component component = component("id");
        component.setCommunication(communication);

        assertThat(ComponentUtils.dependencies(component, null)).isEqualTo(dependencies);
    }

    @Test
    public void dependencies_should_return_dependencies_if_dependencies_is_not_empty() {
        Set<Dependency> dependencies = newHashSet(dependency());
        Communication communication = new Communication();
        communication.setDependencies(dependencies);
        Component component = component("id");
        component.setCommunication(communication);

        assertThat(ComponentUtils.dependencies(component, null)).isEqualTo(dependencies);
    }

    private static Dependency dependency() {
        Dependency dependency = new Dependency();
        dependency.setTarget("target");
        return dependency;
    }

    private static Component component(String id) {
        Component component = new Component();
        component.setId(id);
        return component;
    }

}
