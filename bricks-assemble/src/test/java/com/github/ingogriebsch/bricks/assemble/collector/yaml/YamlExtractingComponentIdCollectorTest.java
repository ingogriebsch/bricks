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

import static org.apache.commons.lang3.StringUtils.replace;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class YamlExtractingComponentIdCollectorTest {

    @Test
    public void ctor_should_throw_exception_if_resource_loader_is_null() {
        assertThrows(NullPointerException.class, () -> new YamlExtractingComponentIdCollector(null));
    }

    @Test
    public void collect_should_throw_exception_if_input_is_null(@Mock YamlResourceLoader yamlResourceLoader) {
        assertThrows(NullPointerException.class, () -> new YamlExtractingComponentIdCollector(yamlResourceLoader).collect(null));
    }

    @Test
    public void collect_should_return_matching_id_if_yaml_contains_component_as_leaf_of_one_tree() throws Exception {
        ApplicationIdOrigin applicationIdOrigin =
            ApplicationIdOrigin.builder().parents(new String[] { "some", "tree", "structure" }).build();
        ComponentIdOrigin componentIdOrigin = ComponentIdOrigin.builder().applicationIdOrigin(applicationIdOrigin).build();
        YamlExtractingComponentIdCollector collector =
            new YamlExtractingComponentIdCollector(getResourceLoader("component_as_leaf_of_one_tree.yaml"), componentIdOrigin);

        assertThat(collector.collect("app1")).isNotNull().containsExactlyInAnyOrder("comp1");
    }

    @Test
    public void collect_should_return_empty_set_if_yaml_contains_component_as_leaf_of_one_tree() throws Exception {
        ApplicationIdOrigin applicationIdOrigin =
            ApplicationIdOrigin.builder().parents(new String[] { "some", "tree", "structure" }).build();
        ComponentIdOrigin componentIdOrigin = ComponentIdOrigin.builder().applicationIdOrigin(applicationIdOrigin).build();
        YamlExtractingComponentIdCollector collector =
            new YamlExtractingComponentIdCollector(getResourceLoader("component_as_leaf_of_one_tree.yaml"), componentIdOrigin);

        assertThat(collector.collect("app10")).isNotNull().isEmpty();
    }

    @Test
    public void collect_should_return_matching_id_if_yaml_contains_component_on_root_level() throws Exception {
        YamlExtractingComponentIdCollector collector = new YamlExtractingComponentIdCollector(
            getResourceLoader("component_on_root_level.yaml"), ComponentIdOrigin.defaultOrigin());

        assertThat(collector.collect("app1")).isNotNull().containsExactlyInAnyOrder("comp1");
    }

    @Test
    public void collect_should_return_empty_set_if_yaml_contains_component_on_root_level() throws Exception {
        YamlExtractingComponentIdCollector collector = new YamlExtractingComponentIdCollector(
            getResourceLoader("component_on_root_level.yaml"), ComponentIdOrigin.defaultOrigin());

        assertThat(collector.collect("app10")).isNotNull().isEmpty();
    }

    @Test
    public void collect_should_return_matching_id_if_yaml_contains_components_as_leafs_of_one_tree() throws Exception {
        ApplicationIdOrigin applicationIdOrigin =
            ApplicationIdOrigin.builder().parents(new String[] { "some", "tree", "structure" }).build();
        ComponentIdOrigin componentIdOrigin = ComponentIdOrigin.builder().applicationIdOrigin(applicationIdOrigin).build();
        YamlExtractingComponentIdCollector collector =
            new YamlExtractingComponentIdCollector(getResourceLoader("components_as_leafs_of_one_tree.yaml"), componentIdOrigin);

        assertThat(collector.collect("app1")).isNotNull().containsExactlyInAnyOrder("comp1", "comp2");
        assertThat(collector.collect("app2")).isNotNull().containsExactlyInAnyOrder("comp3", "comp4");
    }

    @Test
    public void collect_should_return_empty_set_if_yaml_contains_components_as_leafs_of_one_tree() throws Exception {
        ApplicationIdOrigin applicationIdOrigin =
            ApplicationIdOrigin.builder().parents(new String[] { "some", "tree", "structure" }).build();
        ComponentIdOrigin componentIdOrigin = ComponentIdOrigin.builder().applicationIdOrigin(applicationIdOrigin).build();
        YamlExtractingComponentIdCollector collector =
            new YamlExtractingComponentIdCollector(getResourceLoader("components_as_leafs_of_one_tree.yaml"), componentIdOrigin);

        assertThat(collector.collect("app10")).isNotNull().isEmpty();
    }

    @Test
    public void collect_should_return_matching_id_if_yaml_contains_components_as_leafs_of_several_trees() throws Exception {
        ApplicationIdOrigin applicationIdOrigin =
            ApplicationIdOrigin.builder().parents(new String[] { "some", "tree", "structure" }).build();
        ComponentIdOrigin componentIdOrigin = ComponentIdOrigin.builder().applicationIdOrigin(applicationIdOrigin).build();
        YamlExtractingComponentIdCollector collector = new YamlExtractingComponentIdCollector(
            getResourceLoader("components_as_leafs_of_several_trees.yaml"), componentIdOrigin);

        assertThat(collector.collect("app1")).isNotNull().containsExactlyInAnyOrder("comp1", "comp2", "comp3");
        assertThat(collector.collect("app2")).isNotNull().containsExactlyInAnyOrder("comp1");
    }

    @Test
    public void collect_should_return_empty_set_if_yaml_contains_components_as_leafs_of_several_trees() throws Exception {
        ApplicationIdOrigin applicationIdOrigin =
            ApplicationIdOrigin.builder().parents(new String[] { "some", "tree", "structure" }).build();
        ComponentIdOrigin componentIdOrigin = ComponentIdOrigin.builder().applicationIdOrigin(applicationIdOrigin).build();
        YamlExtractingComponentIdCollector collector =
            new YamlExtractingComponentIdCollector(getResourceLoader("components_as_leafs_of_one_tree.yaml"), componentIdOrigin);

        assertThat(collector.collect("app10")).isNotNull().isEmpty();
    }

    @Test
    public void collect_should_return_matching_id_if_yaml_contains_components_on_root_level() throws Exception {
        YamlExtractingComponentIdCollector collector = new YamlExtractingComponentIdCollector(
            getResourceLoader("components_on_root_level.yaml"), ComponentIdOrigin.defaultOrigin());

        assertThat(collector.collect("app1")).isNotNull().containsExactlyInAnyOrder("comp1", "comp2", "comp3");
        assertThat(collector.collect("app2")).isNotNull().containsExactlyInAnyOrder("comp1");
    }

    @Test
    public void collect_should_return_empty_set_if_yaml_contains_components_on_root_level() throws Exception {
        YamlExtractingComponentIdCollector collector = new YamlExtractingComponentIdCollector(
            getResourceLoader("components_on_root_level.yaml"), ComponentIdOrigin.defaultOrigin());

        assertThat(collector.collect("app10")).isNotNull().isEmpty();
    }

    private YamlResourceLoader getResourceLoader(String resourceName) {
        String location = replace(getClass().getPackage().getName(), ".", "/") + "/" + resourceName;
        return new YamlResourceLoader() {

            @Override
            public InputStream load() {
                return getClass().getClassLoader().getResourceAsStream(location);
            }
        };
    }
}
