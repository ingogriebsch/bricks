/*-
 * #%L
 * Bricks Maven Plugin
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
package com.github.ingogriebsch.bricks.maven.plugin;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import static com.fasterxml.jackson.databind.SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS;

import java.util.function.Supplier;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * provides a configured ObjectMapper for serializing the Bricks Model
 * 
 * @author doc
 *
 */
// TODO ingo
// should imho be moved to Model or API Package, so that sharing the
// code makes sure there is only one place where Jackson is configured ?
public class ObjectMapperSupplier implements Supplier<ObjectMapper> {

    private final ObjectMapper objectMapper;

    public ObjectMapperSupplier() {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(NON_NULL);
        objectMapper.enable(ORDER_MAP_ENTRIES_BY_KEYS);
        objectMapper.enable(INDENT_OUTPUT);
    }

    @Override
    public ObjectMapper get() {
        return objectMapper;
    }

}
