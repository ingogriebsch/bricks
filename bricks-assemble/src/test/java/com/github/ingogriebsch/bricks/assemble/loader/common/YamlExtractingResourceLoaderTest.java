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
package com.github.ingogriebsch.bricks.assemble.loader.common;

import static org.apache.commons.lang3.StringUtils.replace;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

public class YamlExtractingResourceLoaderTest {

    @Test
    public void load_should_return_null_if_delegate_returns_null() throws Exception {
        YamlExtractingResourceLoader resourceLoader = new YamlExtractingResourceLoader(new NullResourceLoader());
        assertThat(resourceLoader.load("id")).isNull();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void load_should_return_resource_if_yaml_contains_entries_on_root_level_including_matching_resource()
        throws Exception {
        YamlExtractingResourceLoader resourceLoader =
            new YamlExtractingResourceLoader(getResourceLoader("entries_on_root_level.yaml"));

        Map<String, String> component;
        try (InputStream resource = resourceLoader.load("component2")) {
            component = (Map<String, String>) new Yaml().load(resource);
        }

        assertThat(component).isNotNull().containsEntry("id", "component2");
        assertThat(component).isNotNull().containsEntry("name", "Component 2");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void load_should_return_resource_if_yaml_contains_entry_on_root_level_matching_resource() throws Exception {
        YamlExtractingResourceLoader resourceLoader =
            new YamlExtractingResourceLoader(getResourceLoader("entry_on_root_level.yaml"));

        Map<String, String> component;
        try (InputStream resource = resourceLoader.load("component1")) {
            component = (Map<String, String>) new Yaml().load(resource);
        }

        assertThat(component).isNotNull().containsEntry("id", "component1");
        assertThat(component).isNotNull().containsEntry("name", "Component 1");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void load_should_return_resource_if_yaml_contains_entries_as_leafs_of_one_tree_including_matching_resource()
        throws Exception {
        YamlExtractingResourceLoader resourceLoader = new YamlExtractingResourceLoader(
            getResourceLoader("entries_as_leafs_of_one_tree.yaml"), "id", "some", "tree", "structure");

        Map<String, String> component;
        try (InputStream resource = resourceLoader.load("component2")) {
            component = (Map<String, String>) new Yaml().load(resource);
        }

        assertThat(component).isNotNull().containsEntry("id", "component2");
        assertThat(component).isNotNull().containsEntry("name", "Component 2");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void load_should_return_resource_if_yaml_contains_entries_as_leafs_of_several_trees_including_matching_resource()
        throws Exception {
        YamlExtractingResourceLoader resourceLoader = new YamlExtractingResourceLoader(
            getResourceLoader("entries_as_leafs_of_several_trees.yaml"), "id", "some", "tree", "structure");

        Map<String, String> component;
        try (InputStream resource = resourceLoader.load("component2")) {
            component = (Map<String, String>) new Yaml().load(resource);
        }

        assertThat(component).isNotNull().containsEntry("id", "component2");
        assertThat(component).isNotNull().containsEntry("name", "Component 2");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void load_should_return_resource_if_yaml_contains_entry_as_leaf_of_one_tree_matching_resource() throws Exception {
        YamlExtractingResourceLoader resourceLoader = new YamlExtractingResourceLoader(
            getResourceLoader("entry_as_leaf_of_one_tree.yaml"), "id", "some", "tree", "structure");

        Map<String, String> component;
        try (InputStream resource = resourceLoader.load("component1")) {
            component = (Map<String, String>) new Yaml().load(resource);
        }

        assertThat(component).isNotNull().containsEntry("id", "component1");
        assertThat(component).isNotNull().containsEntry("name", "Component 1");
    }

    @Test
    public void load_should_return_null_if_yaml_contains_entry_as_leaf_of_one_tree_not_matching_resource() throws Exception {
        YamlExtractingResourceLoader resourceLoader = new YamlExtractingResourceLoader(
            getResourceLoader("entry_as_leaf_of_one_tree.yaml"), "id", "some", "tree", "structure");
        assertThat(resourceLoader.load("component2")).isNull();
    }

    private InputStreamResourceLoader getResourceLoader(String resourceName) {
        String location = replace(getClass().getPackage().getName(), ".", "/") + "/" + resourceName;
        InputStream resource = getClass().getClassLoader().getResourceAsStream(location);
        return new InputStreamResourceLoader(resource);
    }
}
