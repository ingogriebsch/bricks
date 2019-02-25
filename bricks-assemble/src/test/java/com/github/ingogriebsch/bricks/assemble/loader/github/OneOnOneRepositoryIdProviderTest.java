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
package com.github.ingogriebsch.bricks.assemble.loader.github;

import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class OneOnOneRepositoryIdProviderTest {

    @Test(expected = NullPointerException.class)
    public void getId_should_throw_exception_if_input_is_null() {
        new OneOnOneRepositoryIdProvider().getId(null);
    }

    @Test
    public void getId_should_return_given_input() {
        RepositoryIdProvider provider = new OneOnOneRepositoryIdProvider();

        newHashSet(randomAlphabetic(6)).stream().forEach(id -> {
            assertThat(provider.getId(id)).isNotNull().isEqualTo(id);
        });
    }
}
