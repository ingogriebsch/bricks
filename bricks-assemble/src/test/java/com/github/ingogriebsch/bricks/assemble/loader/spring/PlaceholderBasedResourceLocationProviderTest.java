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
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PlaceholderBasedResourceLocationProviderTest {

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_input_is_null() {
        new PlaceholderBasedResourceLocationProvider(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_first_parameter_is_null() {
        new PlaceholderBasedResourceLocationProvider(null, "id");
    }

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_second_parameter_is_null() {
        new PlaceholderBasedResourceLocationProvider("classpath:${id}", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creation_should_throw_exception_if_given_placeholderName_is_not_matching_against_locationBase() {
        new PlaceholderBasedResourceLocationProvider("classpath:${one}", "two");
    }

    @Test(expected = IllegalArgumentException.class)
    public void creation_should_throw_exception_if_default_placeholderName_is_not_matching_against_locationBase() {
        new PlaceholderBasedResourceLocationProvider("classpath:${" + randomAlphanumeric(10) + "}");
    }

    @Test(expected = NullPointerException.class)
    public void getLocation_should_throw_exception_if_input_is_null() {
        new PlaceholderBasedResourceLocationProvider("classpath:${id}").get(null);
    }

    @Test
    public void getLocation_should_replace_placeholder_with_given_value() {
        String locationBasePrefix = "classpath:";
        String id = randomAlphabetic(6);

        ResourceLocationProvider provider =
            new PlaceholderBasedResourceLocationProvider(locationBasePrefix + "${placeholderName}", "placeholderName");

        assertThat(provider.get(id)).isNotNull().isEqualTo(locationBasePrefix + id);
    }

}
