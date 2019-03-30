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
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import com.github.ingogriebsch.bricks.model.Component;

import org.junit.jupiter.api.Test;

public class ComponentCountAnalyzerTest {

    @Test
    public void analyzer_should_throw_exception_if_components_is_null() {
        assertThrows(NullPointerException.class, () -> ComponentCountAnalyzer.builder().components(null).build());
    }

    @Test
    public void analyzer_should_return_matching_count_if_no_components_given() {
        assertThrows(NullPointerException.class, () -> ComponentCountAnalyzer.builder().build());
    }

    @Test
    public void analyzer_should_return_matching_count_if_components_given() {
        Set<Component> components = newHashSet(component(), component(), component(), component(), component());
        ComponentCountAnalyzer builder = ComponentCountAnalyzer.builder().components(components).build();
        assertThat(builder.analyze()).isEqualTo(components.size());
    }

    @Test
    public void analyzer_should_return_matching_count_even_if_null_components_given() {
        Set<Component> components = newHashSet(component(), null, component(), null, component());
        ComponentCountAnalyzer builder = ComponentCountAnalyzer.builder().components(components).build();
        assertThat(builder.analyze()).isEqualTo(components.size() - 1);
    }

    private static Component component() {
        return new Component();
    }
}
