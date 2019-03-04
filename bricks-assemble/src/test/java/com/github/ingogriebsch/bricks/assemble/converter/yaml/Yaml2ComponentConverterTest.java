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

import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.ingogriebsch.bricks.model.Component;

import org.junit.BeforeClass;
import org.junit.Test;

public class Yaml2ComponentConverterTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public static void beforeClass() {
        objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.configure(FAIL_ON_EMPTY_BEANS, false);
    }

    @Test(expected = NullPointerException.class)
    public void convert_should_throw_exception_if_input_is_null() throws Exception {
        new Yaml2ComponentConverter().convert(null);
    }

    @Test(expected = IOException.class)
    public void convert_should_throw_exception_if_input_is_not_legal() throws Exception {
        try (InputStream is = new ByteArrayInputStream("test".getBytes())) {
            new Yaml2ComponentConverter().convert(is);
        }
    }

    @Test
    public void convert_should_convert_empty_component_to_matching_output() throws Exception {
        Component input = new Component();

        Component output;
        try (InputStream is = new ByteArrayInputStream(objectMapper.writeValueAsBytes(input))) {
            output = new Yaml2ComponentConverter().convert(is);
        }

        assertThat(output).isNotNull().isEqualTo(input);
    }

    @Test
    public void convert_should_convert_filled_component_to_matching_output() throws Exception {
        Component input = new Component();
        input.setId("id");
        input.setName("name");
        input.setDescription("description");
        input.setVersion("version");
        input.setLayer("layer");

        Component output;
        try (InputStream is = new ByteArrayInputStream(objectMapper.writeValueAsBytes(input))) {
            output = new Yaml2ComponentConverter().convert(is);
        }

        assertThat(output).isNotNull().isEqualTo(input);
    }
}
