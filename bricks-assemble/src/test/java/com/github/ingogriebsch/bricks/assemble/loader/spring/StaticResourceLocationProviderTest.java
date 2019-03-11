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

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StaticResourceLocationProviderTest {

    @Test
    public void creation_should_throw_exception_if_input_is_not_given() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new StaticResourceLocationProvider(null);
        });
    }

    @Test
    public void load_should_return_set_location() {
        String location = "c:/temp";
        StaticResourceLocationProvider locationProvider = new StaticResourceLocationProvider(location);
        assertThat(locationProvider.get(randomAlphabetic(8))).isNotNull().isEqualTo(location);
    }
}
