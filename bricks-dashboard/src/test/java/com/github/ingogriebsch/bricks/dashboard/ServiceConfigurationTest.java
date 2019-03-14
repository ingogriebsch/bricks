/*-
 * #%L
 * Bricks Dashboard
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
package com.github.ingogriebsch.bricks.dashboard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ResourceLoader;

@ExtendWith(MockitoExtension.class)
public class ServiceConfigurationTest {

    @Test
    public void creation_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> new ServiceConfiguration(null));
    }

    @Test
    public void applicationCollector_should_return_instance(@Mock ResourceLoader resourceLoader) {
        assertThat(new ServiceConfiguration(resourceLoader).applicationCollector()).isNotNull();
    }

    @Test
    public void componentCollector_should_return_instance(@Mock ResourceLoader resourceLoader) {
        assertThat(new ServiceConfiguration(resourceLoader).componentCollector()).isNotNull();
    }
}
