/*
 * Copyright 2019 Ingo Griebsch
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package com.github.ingogriebsch.bricks.assemble.collector.common;

import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.Test;

import com.github.ingogriebsch.bricks.assemble.collector.ApplicationIdCollector;

public class StaticApplicationIdCollectorTest {

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_input_is_null() {
        new StaticApplicationIdCollector(null);
    }

    @Test
    public void collect_should_return_given_input() {
        Set<String> applicationIds = newHashSet(randomAlphabetic(6), randomAlphabetic(6), randomAlphabetic(6));
        ApplicationIdCollector collector = new StaticApplicationIdCollector(newHashSet(applicationIds));
        assertThat(collector.collect()).isNotNull().isEqualTo(applicationIds);
    }

}
