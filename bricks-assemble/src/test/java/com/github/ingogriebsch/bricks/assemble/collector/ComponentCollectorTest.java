/*
 * Copyright 2019 Ingo Griebsch
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package com.github.ingogriebsch.bricks.assemble.collector;

import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.github.ingogriebsch.bricks.assemble.reader.ComponentReader;
import com.github.ingogriebsch.bricks.model.Component;

@RunWith(MockitoJUnitRunner.class)
public class ComponentCollectorTest {

    @Mock
    private ComponentIdCollector collector;

    @Mock
    private ComponentReader reader;

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_input_is_null() {
        new ComponentCollector(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_collector_is_null() {
        new ComponentCollector(null, reader);
    }

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_assembler_is_null() {
        new ComponentCollector(collector, null);
    }

    @Test(expected = NullPointerException.class)
    public void collect_should_throw_exception_if_input_is_null() throws Exception {
        new ComponentCollector(collector, reader).collect(null);
    }

    @Test(expected = RuntimeException.class)
    public void collect_throws_exception_if_collector_throws_exception() throws Exception {
        String applicationId = "applicationId";

        given(collector.collect(applicationId)).willThrow(new RuntimeException());

        new ComponentCollector(collector, reader).collect(applicationId);
    }

    @Test(expected = RuntimeException.class)
    public void collect_throws_exception_if_assembler_throws_exception() throws Exception {
        String applicationId = "applicationId";

        given(collector.collect(applicationId)).willReturn(newHashSet(randomAlphabetic(6)));
        given(reader.read(anyString())).willThrow(new RuntimeException());

        new ComponentCollector(collector, reader).collect(applicationId);
    }

    @Test
    public void collect_should_call_collector() throws Exception {
        String applicationId = "applicationId";

        given(collector.collect(applicationId)).willReturn(newHashSet(randomAlphabetic(6)));
        given(reader.read(anyString())).willReturn(null);

        new ComponentCollector(collector, reader).collect(applicationId);

        verify(collector).collect(applicationId);
    }

    @Test
    public void collect_should_call_assembler_based_on_the_collector_output() throws Exception {
        String applicationId = "applicationId";
        String componentId = "componentId";
        Set<String> componentIds = newHashSet(componentId, randomAlphabetic(6), randomAlphabetic(6));

        given(collector.collect(applicationId)).willReturn(componentIds);
        given(reader.read(componentId)).willAnswer(new Answer<Component>() {

            @Override
            public Component answer(InvocationOnMock invocation) throws Throwable {
                return componentId.equals(invocation.getArguments()[0]) ? new Component() : null;
            }
        });

        new ComponentCollector(collector, reader).collect(applicationId);

        verify(collector).collect(applicationId);
        verify(reader, times(componentIds.size())).read(anyString());
    }

    @Test
    public void collect_should_only_return_available_components() throws Exception {
        String applicationId = "applicationId";
        String componentId = "componentId";
        Set<String> componentIds = newHashSet(componentId, randomAlphabetic(6), randomAlphabetic(6));

        given(collector.collect(applicationId)).willReturn(componentIds);
        given(reader.read(componentId)).willAnswer(new Answer<Component>() {

            @Override
            public Component answer(InvocationOnMock invocation) throws Throwable {
                return componentId.equals(invocation.getArguments()[0]) ? new Component() : null;
            }
        });

        Set<Component> components = new ComponentCollector(collector, reader).collect(applicationId);
        assertThat(components).isNotNull().hasSize(1);

        verify(reader, times(componentIds.size())).read(anyString());
    }
}
