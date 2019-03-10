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
package com.github.ingogriebsch.bricks.assemble.loader.spring;

import static org.apache.commons.lang3.RandomStringUtils.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.DescriptiveResource;
import org.springframework.core.io.ResourceLoader;

@ExtendWith(MockitoExtension.class)
public class SpringResourceBasedResourceLoaderTest {

    @Mock
    private ResourceLoader resourceLoader;

    @Mock
    private ResourceLocationProvider resourceLocationProvider;

    @Test
    public void creation_should_throw_exception_if_input_is_null() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new SpringResourceBasedResourceLoader(null, null);
        });
    }

    @Test
    public void creation_should_throw_exception_if_first_parameter_is_null() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new SpringResourceBasedResourceLoader(null, resourceLocationProvider);
        });
    }

    @Test
    public void creation_should_throw_exception_if_second_parameter_is_null() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new SpringResourceBasedResourceLoader(resourceLoader, null);
        });
    }

    @Test
    public void creation_should_succeed_if_all_input_parameters_are_given() {
        new SpringResourceBasedResourceLoader(resourceLoader, resourceLocationProvider);
    }

    @Test
    public void load_should_throw_exception_if_input_is_null() throws IOException {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new SpringResourceBasedResourceLoader(resourceLoader, resourceLocationProvider).load(null);
        });
    }

    @Test
    public void load_should_return_null_if_resource_does_not_exist() throws IOException {
        String id = randomAlphabetic(8);
        given(resourceLocationProvider.get(id)).willReturn(id);
        given(resourceLoader.getResource(id)).willReturn(new DescriptiveResource(id));
        SpringResourceBasedResourceLoader springResourceBasedResourceLoader =
            new SpringResourceBasedResourceLoader(resourceLoader, resourceLocationProvider);
        assertThat(springResourceBasedResourceLoader.load(id)).isNull();
    }

    @Test
    public void load_should_return_stream_if_resource_does_exist() throws IOException {
        String id = randomAlphabetic(8);
        given(resourceLocationProvider.get(id)).willReturn(id);
        given(resourceLoader.getResource(id)).willReturn(new ByteArrayResource(random(128).getBytes(), id));
        SpringResourceBasedResourceLoader springResourceBasedResourceLoader =
            new SpringResourceBasedResourceLoader(resourceLoader, resourceLocationProvider);
        try (InputStream stream = springResourceBasedResourceLoader.load(id)) {
            assertThat(stream).isNotNull();
        }
    }
}
