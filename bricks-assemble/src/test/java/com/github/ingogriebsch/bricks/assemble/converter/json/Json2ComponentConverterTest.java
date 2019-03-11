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

import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ingogriebsch.bricks.model.Component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Json2ComponentConverterTest {

    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void beforeClass() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(FAIL_ON_EMPTY_BEANS, false);
    }

    @Test
    public void convert_should_throw_exception_if_input_is_null() throws Exception {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new Json2ComponentConverter().convert(null, null);
        });
    }

    @Test
    public void convert_should_throw_exception_if_input_is_not_legal() throws Exception {
        Assertions.assertThrows(IOException.class, () -> {
            try (InputStream is = new ByteArrayInputStream("test".getBytes())) {
                new Json2ComponentConverter().convert(is, "regardless");
            }
        });
    }

    @Test
    public void convert_should_convert_empty_component_to_matching_output() throws Exception {
        Component source = new Component();
        Component target;
        try (InputStream is = new ByteArrayInputStream(objectMapper.writeValueAsBytes(source))) {
            target = new Json2ComponentConverter().convert(is, "regardless");
        }
        assertThat(target).isNotNull().isEqualTo(source);
    }

    @Test
    public void convert_should_convert_filled_component_to_matching_output() throws Exception {
        Component source = new Component();
        source.setId("id");
        source.setName("name");
        source.setDescription("description");
        source.setVersion("version");
        source.setLayer("layer");
        Component target;
        try (InputStream is = new ByteArrayInputStream(objectMapper.writeValueAsBytes(source))) {
            target = new Json2ComponentConverter().convert(is, source.getId());
        }
        assertThat(target).isNotNull().isEqualTo(source);
    }
}
