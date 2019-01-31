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

import com.github.ingogriebsch.bricks.assemble.assembler.ComponentAssembler;
import com.github.ingogriebsch.bricks.model.Component;

@RunWith(MockitoJUnitRunner.class)
public class ComponentCollectorTest {

    @Mock
    private ComponentIdCollector collector;

    @Mock
    private ComponentAssembler assembler;

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_input_is_null() {
        new ComponentCollector(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_collector_is_null() {
        new ComponentCollector(null, assembler);
    }

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_assembler_is_null() {
        new ComponentCollector(collector, null);
    }

    @Test(expected = NullPointerException.class)
    public void collect_should_throw_exception_if_input_is_null() throws Exception {
        new ComponentCollector(collector, assembler).collect(null);
    }

    @Test(expected = RuntimeException.class)
    public void collect_throws_exception_if_collector_throws_exception() throws Exception {
        String applicationId = "applicationId";

        given(collector.collect(applicationId)).willThrow(new RuntimeException());

        new ComponentCollector(collector, assembler).collect(applicationId);
    }

    @Test(expected = RuntimeException.class)
    public void collect_throws_exception_if_assembler_throws_exception() throws Exception {
        String applicationId = "applicationId";

        given(collector.collect(applicationId)).willReturn(newHashSet(randomAlphabetic(6)));
        given(assembler.assemble(anyString())).willThrow(new RuntimeException());

        new ComponentCollector(collector, assembler).collect(applicationId);
    }

    @Test
    public void collect_should_call_collector() throws Exception {
        String applicationId = "applicationId";

        given(collector.collect(applicationId)).willReturn(newHashSet(randomAlphabetic(6)));
        given(assembler.assemble(anyString())).willReturn(null);

        new ComponentCollector(collector, assembler).collect(applicationId);

        verify(collector).collect(applicationId);
    }

    @Test
    public void collect_should_call_assembler_based_on_the_collector_output() throws Exception {
        String applicationId = "applicationId";
        String componentId = "componentId";
        Set<String> componentIds = newHashSet(componentId, randomAlphabetic(6), randomAlphabetic(6));

        given(collector.collect(applicationId)).willReturn(componentIds);
        given(assembler.assemble(componentId)).willAnswer(new Answer<Component>() {

            @Override
            public Component answer(InvocationOnMock invocation) throws Throwable {
                return componentId.equals(invocation.getArguments()[0]) ? new Component() : null;
            }
        });

        new ComponentCollector(collector, assembler).collect(applicationId);

        verify(collector).collect(applicationId);
        verify(assembler, times(componentIds.size())).assemble(anyString());
    }

    @Test
    public void collect_should_only_return_available_components() throws Exception {
        String applicationId = "applicationId";
        String componentId = "componentId";
        Set<String> componentIds = newHashSet(componentId, randomAlphabetic(6), randomAlphabetic(6));

        given(collector.collect(applicationId)).willReturn(componentIds);
        given(assembler.assemble(componentId)).willAnswer(new Answer<Component>() {

            @Override
            public Component answer(InvocationOnMock invocation) throws Throwable {
                return componentId.equals(invocation.getArguments()[0]) ? new Component() : null;
            }
        });

        Set<Component> components = new ComponentCollector(collector, assembler).collect(applicationId);
        assertThat(components).isNotNull().hasSize(1);

        verify(assembler, times(componentIds.size())).assemble(anyString());
    }
}
