/*-
 * #%L
 * Bricks Analyze
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
package com.github.ingogriebsch.bricks.analyze;

import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class IterableUtilsTest {

    @Test
    public void isNullOrEmpty_should_return_true_if_input_is_null() {
        assertThat(IterableUtils.isNullOrEmpty(null)).isTrue();
    }

    @Test
    public void isNullOrEmpty_should_return_true_if_input_is_empty() {
        assertThat(IterableUtils.isNullOrEmpty(newHashSet())).isTrue();
    }

    @Test
    public void isNullOrEmpty_should_return_false_if_input_is_not_empty() {
        assertThat(IterableUtils.isNullOrEmpty(newHashSet("Some", "value"))).isFalse();
    }
}
