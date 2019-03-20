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

import static java.util.Arrays.copyOfRange;

import static com.google.common.collect.Sets.newHashSet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.github.ingogriebsch.bricks.assemble.collector.ApplicationIdCollector;

import org.yaml.snakeyaml.Yaml;

import lombok.NonNull;
import lombok.SneakyThrows;

public class YamlExtractingApplicationIdCollector implements ApplicationIdCollector {

    private static final Yaml yaml = new Yaml();

    private final ResourceLoader resourceLoader;
    private final String identifier;
    private final String[] parents;

    public YamlExtractingApplicationIdCollector(@NonNull ResourceLoader resourceLoader) {
        this(resourceLoader, "id");
    }

    public YamlExtractingApplicationIdCollector(@NonNull ResourceLoader resourceLoader, @NonNull String identifier,
        String... parents) {
        this.resourceLoader = resourceLoader;
        this.identifier = identifier;
        this.parents = parents;
    }

    @Override
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public Set<String> collect() {
        InputStream resource = resourceLoader.load();
        if (resource == null) {
            return null;
        }

        Set<Map<String, Object>> trees = load(resource);
        if (trees == null || trees.isEmpty()) {
            return newHashSet();
        }

        Set<String> result = newHashSet();
        for (Map<String, Object> tree : trees) {
            for (Map<String, Object> entry : access(newHashSet(tree), parents)) {
                Object id = entry.get(identifier);
                if (id != null && id instanceof String) {
                    result.add((String) id);
                }
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private static Set<Map<String, Object>> load(InputStream resource) throws IOException {
        Set<Map<String, Object>> result = newHashSet();
        try {
            yaml.loadAll(resource).forEach(o -> result.add((Map<String, Object>) o));
        } finally {
            closeQuietly(resource);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private static Set<Map<String, Object>> access(Set<Map<String, Object>> things, String... parents) {
        if (parents == null || parents.length == 0) {
            return things;
        }

        Set<Map<String, Object>> result = newHashSet();
        for (Map<String, Object> thing : things) {
            Object object = thing.get(parents[0]);

            if (object instanceof Collection) {
                result.addAll((Collection<Map<String, Object>>) object);
            } else if (object instanceof Map) {
                result.add((Map<String, Object>) object);
            }
        }

        return access(result, copyOfRange(parents, 1, parents.length));
    }

    private static void closeQuietly(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (Exception e) {
            }
        }
    }
}
