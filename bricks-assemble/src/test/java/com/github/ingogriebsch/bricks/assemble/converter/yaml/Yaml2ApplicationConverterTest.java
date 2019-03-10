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

import static java.nio.charset.Charset.*;
import static java.util.stream.Collectors.*;

import static com.fasterxml.jackson.databind.SerializationFeature.*;
import static org.apache.commons.beanutils.BeanUtils.*;
import static org.apache.commons.io.IOUtils.*;
import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.ingogriebsch.bricks.model.Application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Yaml2ApplicationConverterTest {

    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void beforeClass() {
        objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.configure(FAIL_ON_EMPTY_BEANS, false);
    }

    @Test
    public void convert_should_throw_exception_if_input_is_null() throws Exception {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new Yaml2ApplicationConverter().convert(null, null);
        });
    }

    @Test
    public void convert_should_throw_exception_if_input_is_not_legal() throws Exception {
        Assertions.assertThrows(IOException.class, () -> {
            try (InputStream is = new ByteArrayInputStream("test".getBytes())) {
                new Yaml2ApplicationConverter().convert(is, "regardless");
            }
        });
    }

    @Test
    public void convert_should_convert_empty_application_to_matching_output() throws Exception {
        Application source = new Application();
        Application target;
        try (InputStream is = new ByteArrayInputStream(objectMapper.writeValueAsBytes(source))) {
            target = new Yaml2ApplicationConverter().convert(is, "regardless");
        }
        assertThat(target).isNotNull().isEqualTo(source);
    }

    @Test
    public void convert_should_convert_application_to_matching_output() throws Exception {
        Application source = new Application();
        source.setId("id");
        source.setName("name");
        source.setDescription("description");
        source.setVersion("version");
        Application target;
        try (InputStream is = toInputStream(objectMapper.writeValueAsString(source), forName("UTF-8"))) {
            target = new Yaml2ApplicationConverter().convert(is, source.getId());
        }
        assertThat(target).isNotNull().isEqualTo(source);
    }

    @Test
    public void convert_should_convert_application_even_if_underlying_resource_contains_content_not_related_to_the_model()
        throws Exception {
        Map<String, String> source = new HashMap<>();
        source.put("id", "id");
        source.put("name", "name");
        source.put("description", "description");
        source.put("version", "version");
        source.put("components", "components");
        source.put("prop", "prop");
        Application target;
        try (InputStream is = toInputStream(objectMapper.writeValueAsString(source), forName("UTF-8"))) {
            target = new Yaml2ApplicationConverter().convert(is, source.get("id"));
        }
        assertThat(target).isNotNull();
        Map<String, String> description = describe(target).entrySet().stream()
            .filter(e -> !e.getKey().equals("class") && e.getValue() != null).collect(toMap(e -> e.getKey(), e -> e.getValue()));
        assertThat(source).containsAllEntriesOf(description);
    }
}
