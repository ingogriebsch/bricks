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
package com.github.ingogriebsch.bricks.assemble.reader.common;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.validation.ValidationException;

import com.github.ingogriebsch.bricks.assemble.loader.ResourceLoader;
import com.github.ingogriebsch.bricks.converter.ComponentConverter;
import com.github.ingogriebsch.bricks.model.Component;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SimpleComponentReaderTest {

    @Mock
    private ResourceLoader resourceLoader;
    @Mock
    private ComponentConverter componentConverter;

    @Test
    public void creation_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> {
            new SimpleComponentReader(null, null);
        });
    }

    @Test
    public void creation_should_throw_exception_if_first_parameter_is_null() {
        assertThrows(NullPointerException.class, () -> {
            new SimpleComponentReader(null, componentConverter);
        });
    }

    @Test
    public void creation_should_throw_exception_if_second_parameter_is_null() {
        assertThrows(NullPointerException.class, () -> {
            new SimpleComponentReader(resourceLoader, null);
        });
    }

    @Test
    public void creation_should_succeed_if_input_is_given() {
        new SimpleComponentReader(resourceLoader, componentConverter);
    }

    @Test
    public void read_should_throw_exception_if_input_is_null() throws Exception {
        assertThrows(NullPointerException.class, () -> {
            new SimpleComponentReader(resourceLoader, componentConverter).read(null);
        });
    }

    @Test
    public void read_should_return_null_if_resource_is_not_available() throws Exception {
        String id = randomAlphabetic(10);

        given(resourceLoader.load(id)).willReturn(null);

        SimpleComponentReader simpleComponentReader = new SimpleComponentReader(resourceLoader, componentConverter);

        assertThat(simpleComponentReader.read(id)).isNull();
        verify(componentConverter, times(0)).from(any(), anyString());
    }

    @Test
    public void read_should_return_component_if_resource_is_available() throws Exception {
        String id = randomAlphabetic(10);
        InputStream stream = new ByteArrayInputStream("{}".getBytes());

        given(resourceLoader.load(id)).willReturn(stream);
        given(componentConverter.from(stream, id)).willReturn(new Component());

        SimpleComponentReader simpleComponentReader = new SimpleComponentReader(resourceLoader, componentConverter, false);

        assertThat(simpleComponentReader.read(id)).isNotNull();
    }

    @Test
    public void read_should_throw_exception_if_component_is_not_valid() throws Exception {
        String id = randomAlphabetic(10);
        InputStream stream = new ByteArrayInputStream("{}".getBytes());

        given(resourceLoader.load(id)).willReturn(stream);
        given(componentConverter.from(stream, id)).willReturn(new Component());

        assertThrows(ValidationException.class, () -> {
            new SimpleComponentReader(resourceLoader, componentConverter, true).read(id);
        });
    }
}
