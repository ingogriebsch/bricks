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

import static java.nio.charset.Charset.forName;

import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ingogriebsch.bricks.model.Application;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.NullInputStream;
import org.apache.commons.io.input.ReaderInputStream;
import org.apache.commons.io.output.NullOutputStream;
import org.apache.commons.io.output.WriterOutputStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Json2ApplicationConverterTest {

    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void beforeAll() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(FAIL_ON_EMPTY_BEANS, false);
    }

    @Test
    public void from_should_throw_exception_if_input_is_null() throws Exception {
        assertThrows(NullPointerException.class, () -> {
            new Json2ApplicationConverter().from(null, null);
        });
    }

    @Test
    public void from_should_throw_exception_if_component_is_null() throws Exception {
        assertThrows(NullPointerException.class, () -> {
            new Json2ApplicationConverter().from(null, "regardless");
        });
    }

    @Test
    public void from_should_throw_exception_if_id_is_null() throws Exception {
        assertThrows(NullPointerException.class, () -> {
            new Json2ApplicationConverter().from(new NullInputStream(0), null);
        });
    }

    @Test
    public void from_should_throw_exception_if_input_is_not_legal() throws Exception {
        assertThrows(IOException.class, () -> {
            try (InputStream is = toInputStream("test")) {
                new Json2ApplicationConverter().from(is, "regardlesse");
            }
        });
    }

    @Test
    public void from_should_convert_empty_application_to_matching_output() throws Exception {
        Application source = new Application();

        Application target;
        try (InputStream is = toInputStream(source)) {
            target = new Json2ApplicationConverter().from(is, "regardless");
        }

        assertThat(target).isNotNull().isEqualTo(source);
    }

    @Test
    public void from_should_convert_filled_application_to_matching_output() throws Exception {
        Application source = new Application();
        source.setId("id");
        source.setName("name");
        source.setDescription("description");
        source.setVersion("version");

        Application target;
        try (InputStream is = toInputStream(source)) {
            target = new Json2ApplicationConverter().from(is, source.getId());
        }

        assertThat(target).isNotNull().isEqualTo(source);
    }

    @Test
    public void to_should_throw_exception_if_input_is_null() throws Exception {
        assertThrows(NullPointerException.class, () -> {
            new Json2ApplicationConverter().to(null, null);
        });
    }

    @Test
    public void to_should_throw_exception_if_application_is_null() throws Exception {
        assertThrows(NullPointerException.class, () -> {
            new Json2ApplicationConverter().to(null, new NullOutputStream());
        });
    }

    @Test
    public void to_should_throw_exception_if_target_is_null() throws Exception {
        assertThrows(NullPointerException.class, () -> {
            new Json2ApplicationConverter().to(new Application(), null);
        });
    }

    @Test
    public void to_should_convert_empty_application_to_matching_output() throws Exception {
        Application source = new Application();

        String raw;
        try (StringWriter writer = new StringWriter()) {
            try (OutputStream os = new WriterOutputStream(writer, forName("UTF-8"))) {
                new Json2ApplicationConverter().to(source, os);
            }
            raw = writer.toString();
        }
        assertThat(raw).isNotNull();

        Application target;
        try (InputStream is = new ReaderInputStream(new StringReader(raw), forName("UTF-8"))) {
            target = new Json2ApplicationConverter().from(is, "regardless");
        }
        assertThat(target).isEqualTo(source);
    }

    @Test
    public void to_should_convert_filled_application_to_matching_output() throws Exception {
        Application source = new Application();
        source.setId("id");
        source.setName("name");
        source.setDescription("description");
        source.setVersion("version");

        String raw;
        try (StringWriter writer = new StringWriter()) {
            try (OutputStream os = new WriterOutputStream(writer, forName("UTF-8"))) {
                new Json2ApplicationConverter().to(source, os);
            }
            raw = writer.toString();
        }
        assertThat(raw).isNotNull();

        Application target;
        try (InputStream is = new ReaderInputStream(new StringReader(raw), forName("UTF-8"))) {
            target = new Json2ApplicationConverter().from(is, "regardless");
        }
        assertThat(target).isEqualTo(source);
    }

    private static InputStream toInputStream(Application application) throws JsonProcessingException {
        return toInputStream(objectMapper.writeValueAsString(application));
    }

    private static InputStream toInputStream(String content) throws JsonProcessingException {
        return IOUtils.toInputStream(content, forName("UTF-8"));
    }
}
