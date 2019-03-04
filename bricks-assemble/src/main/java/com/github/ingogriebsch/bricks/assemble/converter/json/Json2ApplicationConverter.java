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
package com.github.ingogriebsch.bricks.assemble.converter.json;

import static com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ingogriebsch.bricks.assemble.converter.ApplicationConverter;
import com.github.ingogriebsch.bricks.model.Application;

import lombok.NonNull;

public class Json2ApplicationConverter implements ApplicationConverter {

    private final ObjectMapper objectMapper;

    public Json2ApplicationConverter() {
        objectMapper = createAndPrepareObjectMapper();
    }

    @Override
    public Application convert(@NonNull InputStream source, @NonNull String id) throws IOException {
        return objectMapper.readValue(source, Application.class);
    }

    private static ObjectMapper createAndPrepareObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonFactory jsonFactory = objectMapper.getFactory();
        jsonFactory.enable(ALLOW_COMMENTS);
        return objectMapper;
    }
}
