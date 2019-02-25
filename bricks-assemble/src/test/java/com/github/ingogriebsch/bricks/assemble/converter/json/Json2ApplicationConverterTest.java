/*
 * Copyright © 2018 Ingo Griebsch (https://ingogriebsch.de/)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.ingogriebsch.bricks.assemble.converter.json;

import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ingogriebsch.bricks.model.Application;

public class Json2ApplicationConverterTest {

    @Test(expected = NullPointerException.class)
    public void convert_should_throw_exception_if_input_is_null() throws Exception {
        new Json2ApplicationConverter().convert(null);
    }

    @Test(expected = IOException.class)
    public void convert_should_throw_exception_if_input_is_not_legal() throws Exception {
        try (InputStream is = new ByteArrayInputStream("test".getBytes())) {
            new Json2ApplicationConverter().convert(is);
        }
    }

    @Test
    public void convert_should_convert_empty_application_to_matching_output() throws Exception {
        Application input = new Application();

        Application output;
        try (InputStream is = new ByteArrayInputStream(new ObjectMapper().writeValueAsBytes(input))) {
            output = new Json2ApplicationConverter().convert(is);
        }

        assertThat(output).isNotNull().isEqualTo(input);
    }

    @Test
    public void convert_should_convert_filled_application_to_matching_output() throws Exception {
        Application input = new Application("id", "name", "description", "version", newHashSet(), newHashSet());

        Application output;
        try (InputStream is = new ByteArrayInputStream(new ObjectMapper().writeValueAsBytes(input))) {
            output = new Json2ApplicationConverter().convert(is);
        }

        assertThat(output).isNotNull().isEqualTo(input);
    }
}
