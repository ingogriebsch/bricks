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
package com.github.ingogriebsch.bricks.assemble.loader.yaml;

import static java.nio.charset.Charset.forName;
import static java.util.Arrays.copyOfRange;

import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.io.IOUtils.toInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.github.ingogriebsch.bricks.assemble.loader.ResourceLoader;

import org.yaml.snakeyaml.Yaml;

import lombok.NonNull;

public class YamlExtractingResourceLoader implements ResourceLoader {

    private static final Yaml yaml = new Yaml();

    private final ResourceLoader delegate;
    private final String identifier;
    private final String[] parents;

    public YamlExtractingResourceLoader(@NonNull ResourceLoader delegate) {
        this(delegate, "id");
    }

    public YamlExtractingResourceLoader(@NonNull ResourceLoader delegate, @NonNull String identifier, String... parents) {
        this.delegate = delegate;
        this.identifier = identifier;
        this.parents = parents;
    }

    @SuppressWarnings("unchecked")
    @Override
    public InputStream load(@NonNull String id) throws IOException {
        InputStream resource = delegate.load(id);
        if (resource == null) {
            return null;
        }

        Set<Map<String, Object>> trees = load(resource);
        if (trees == null || trees.isEmpty()) {
            return null;
        }

        for (Map<String, Object> tree : trees) {
            for (Map<String, Object> entry : access(newHashSet(tree), parents)) {
                if (id.equals(entry.get(identifier))) {
                    return toInputStream(yaml.dump(entry), forName("UTF-8"));
                }
            }
        }
        return null;
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

    @SuppressWarnings("unchecked")
    private static Set<Map<String, Object>> load(InputStream resource) {
        Set<Map<String, Object>> result = newHashSet();
        yaml.loadAll(resource).forEach(o -> result.add((Map<String, Object>) o));
        return result;
    }
}
