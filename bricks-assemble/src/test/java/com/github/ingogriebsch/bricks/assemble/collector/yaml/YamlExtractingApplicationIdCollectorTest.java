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

public class YamlExtractingApplicationIdCollectorTest {

    @Test
    public void ctor_should_throw_exception_if_resource_loader_is_null() {
        assertThrows(NullPointerException.class, () -> new YamlExtractingApplicationIdCollector(null, "identifier"));
    }

    @Test
    public void collect_should_return_matching_id_if_yaml_contains_application_as_leaf_of_one_tree() throws Exception {
        YamlExtractingApplicationIdCollector collector = new YamlExtractingApplicationIdCollector(
            getResourceLoader("application_as_leaf_of_one_tree.yaml"), "id", "some", "tree", "structure");

        assertThat(collector.collect()).isNotNull().contains("app1");
    }

    @Test
    public void collect_should_return_matching_id_if_yaml_contains_application_on_root_level() throws Exception {
        YamlExtractingApplicationIdCollector collector =
            new YamlExtractingApplicationIdCollector(getResourceLoader("application_on_root_level.yaml"));

        assertThat(collector.collect()).isNotNull().contains("app1");
    }

    @Test
    public void collect_should_return_matching_ids_if_yaml_contains_applications_as_leaf_of_one_tree() throws Exception {
        YamlExtractingApplicationIdCollector collector = new YamlExtractingApplicationIdCollector(
            getResourceLoader("applications_as_leafs_of_one_tree.yaml"), "id", "some", "tree", "structure");

        assertThat(collector.collect()).isNotNull().contains("app1", "app2", "app3");
    }

    @Test
    public void collect_should_return_matching_ids_if_yaml_contains_applications_as_leaf_of_several_trees() throws Exception {
        YamlExtractingApplicationIdCollector collector = new YamlExtractingApplicationIdCollector(
            getResourceLoader("applications_as_leafs_of_several_trees.yaml"), "id", "some", "tree", "structure");

        assertThat(collector.collect()).isNotNull().contains("app1", "app2", "app3");
    }

    @Test
    public void collect_should_return_matching_ids_if_yaml_contains_applications_on_root_level() throws Exception {
        YamlExtractingApplicationIdCollector collector =
            new YamlExtractingApplicationIdCollector(getResourceLoader("applications_on_root_level.yaml"));

        assertThat(collector.collect()).isNotNull().contains("app1", "app2", "app3");
    }

    private ResourceLoader getResourceLoader(String resourceName) {
        String location = replace(getClass().getPackage().getName(), ".", "/") + "/" + resourceName;
        InputStream resource = getClass().getClassLoader().getResourceAsStream(location);
        return new ResourceLoader() {

            @Override
            public InputStream load() {
                return resource;
            }
        };
    }
}
