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
package com.github.ingogriebsch.bricks.assemble.converter.yaml;

import static com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.ingogriebsch.bricks.assemble.converter.ComponentConverter;
import com.github.ingogriebsch.bricks.model.Component;

import lombok.NonNull;

public class Yaml2ComponentConverter implements ComponentConverter {

    private final ObjectMapper objectMapper;

    public Yaml2ComponentConverter() {
        objectMapper = createAndPrepareObjectMapper();
    }

    @Override
    public Component convert(@NonNull InputStream source, @NonNull String id) throws IOException {
        return objectMapper.readValue(source, Component.class);
    }

    private static ObjectMapper createAndPrepareObjectMapper() {
        YAMLFactory yamlFactory = new YAMLFactory();
        yamlFactory.enable(ALLOW_COMMENTS);

        ObjectMapper objectMapper = new ObjectMapper(yamlFactory);
        objectMapper.configure(FAIL_ON_EMPTY_BEANS, false);
        return objectMapper;
    }
}