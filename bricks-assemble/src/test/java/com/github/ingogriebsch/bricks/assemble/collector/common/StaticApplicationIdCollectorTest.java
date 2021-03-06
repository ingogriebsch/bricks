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
package com.github.ingogriebsch.bricks.assemble.collector.common;

import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import com.github.ingogriebsch.bricks.assemble.collector.ApplicationIdCollector;

import org.junit.jupiter.api.Test;

public class StaticApplicationIdCollectorTest {

    @Test
    public void creation_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> {
            new StaticApplicationIdCollector(null);
        });
    }

    @Test
    public void collect_should_return_given_input() {
        Set<String> applicationIds = newHashSet(randomAlphabetic(6), randomAlphabetic(6), randomAlphabetic(6));

        ApplicationIdCollector collector = new StaticApplicationIdCollector(newHashSet(applicationIds));
        assertThat(collector.collect()).isNotNull().isEqualTo(applicationIds);
    }
}
