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
package com.github.ingogriebsch.bricks.dashboard.service;

import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import java.util.Set;

import com.github.ingogriebsch.bricks.assemble.collector.ComponentCollector;
import com.github.ingogriebsch.bricks.model.Component;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ComponentServiceTest {

    @Test
    public void creation_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> {
            new ComponentService(null);
        });
    }

    @Test
    public void findAll_should_throw_exception_if_input_is_null(@Mock ComponentCollector collector) {
        assertThrows(NullPointerException.class, () -> {
            new ComponentService(collector).findAll(null);
        });
    }

    @Test
    public void findAll_should_return_empty_set_if_no_components_are_available(@Mock ComponentCollector collector)
        throws Exception {
        String applicationId = randomNumeric(8);
        given(collector.collect(applicationId)).willReturn(newHashSet());

        ComponentService service = new ComponentService(collector);
        assertThat(service.findAll(applicationId)).isNotNull().isEmpty();
    }

    @Test
    public void findAll_should_return_the_components_the_collector_returns(@Mock ComponentCollector collector) throws Exception {
        String applicationId = randomNumeric(8);
        Set<Component> components =
            newHashSet(component(randomNumeric(8)), component(randomNumeric(8)), component(randomNumeric(8)));
        given(collector.collect(applicationId)).willReturn(components);

        ComponentService service = new ComponentService(collector);
        assertThat(service.findAll(applicationId)).isNotNull().isEqualTo(components);
    }

    @Test
    public void findOne_should_throw_exception_if_input_is_null(@Mock ComponentCollector collector) {
        assertThrows(NullPointerException.class, () -> {
            new ComponentService(collector).findOne(null, null);
        });
    }

    @Test
    public void findOne_should_return_empty_optional_if_component_is_not_available(@Mock ComponentCollector collector)
        throws Exception {
        String applicationId = randomNumeric(8);
        given(collector.collect(applicationId)).willReturn(newHashSet());
        ComponentService service = new ComponentService(collector);

        Optional<Component> optional = service.findOne(applicationId, randomNumeric(8));
        assertThat(optional).isNotNull().isEmpty();
    }

    @Test
    public void findOne_should_return_optional_containing_component_if_available(@Mock ComponentCollector collector)
        throws Exception {
        String applicationId = randomNumeric(8);
        Component component = component(randomNumeric(8));
        given(collector.collect(applicationId)).willReturn(newHashSet(component));
        ComponentService service = new ComponentService(collector);

        Optional<Component> optional = service.findOne(applicationId, component.getId());
        assertThat(optional).isNotNull().contains(component);
    }

    private static Component component(String id) {
        Component component = new Component();
        component.setId(id);
        return component;
    }

}
