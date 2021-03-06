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

import com.github.ingogriebsch.bricks.assemble.reader.ComponentReader;
import com.github.ingogriebsch.bricks.model.Component;

import org.junit.jupiter.api.Test;

public class StaticComponentReaderFactoryTest {

    @Test
    public void ctor_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> new StaticComponentReaderFactory(null));
    }

    @Test
    public void create_should_return_component_reader_instance_used_during_creation() {
        ComponentReader componentReader = componentReader();
        StaticComponentReaderFactory factory = new StaticComponentReaderFactory(componentReader);
        assertThat(factory.create(randomAlphabetic(8))).isEqualTo(componentReader);
        assertThat(factory.create(randomAlphabetic(8))).isEqualTo(componentReader);
    }

    private static ComponentReader componentReader() {
        return new ComponentReader() {

            @Override
            public Component read(String id) throws Exception {
                return null;
            }
        };
    }

}
