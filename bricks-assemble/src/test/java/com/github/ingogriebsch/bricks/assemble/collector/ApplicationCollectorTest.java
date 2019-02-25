package com.github.ingogriebsch.bricks.assemble.collector;

import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Set;

import com.github.ingogriebsch.bricks.assemble.assembler.ApplicationAssembler;
import com.github.ingogriebsch.bricks.model.Application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationCollectorTest {

    @Mock
    private ApplicationIdCollector collector;

    @Mock
    private ApplicationAssembler assembler;

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_input_is_null() {
        new ApplicationCollector(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_collector_is_null() {
        new ApplicationCollector(null, assembler);
    }

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_assembler_is_null() {
        new ApplicationCollector(collector, null);
    }

    @Test(expected = RuntimeException.class)
    public void collect_throws_exception_if_collector_throws_exception() throws Exception {
        given(collector.collect()).willThrow(new RuntimeException());

        new ApplicationCollector(collector, assembler).collect();
    }

    @Test(expected = RuntimeException.class)
    public void collect_throws_exception_if_assembler_throws_exception() throws Exception {
        given(collector.collect()).willReturn(newHashSet(randomAlphabetic(6)));
        given(assembler.assemble(anyString())).willThrow(new RuntimeException());

        new ApplicationCollector(collector, assembler).collect();
    }

    @Test
    public void collect_should_call_collector() throws Exception {
        given(collector.collect()).willReturn(newHashSet(randomAlphabetic(6)));
        given(assembler.assemble(anyString())).willReturn(null);

        new ApplicationCollector(collector, assembler).collect();

        verify(collector).collect();
    }

    @Test
    public void collect_should_call_assembler_based_on_the_collector_output() throws Exception {
        String applicationId = "applicationId";
        Set<String> applicationIds = newHashSet(applicationId, randomAlphabetic(6), randomAlphabetic(6));

        given(collector.collect()).willReturn(applicationIds);
        given(assembler.assemble(anyString())).willAnswer(new Answer<Application>() {

            @Override
            public Application answer(InvocationOnMock invocation) throws Throwable {
                return applicationId.equals(invocation.getArguments()[0]) ? new Application() : null;
            }
        });

        new ApplicationCollector(collector, assembler).collect();

        verify(collector).collect();
        verify(assembler, times(applicationIds.size())).assemble(anyString());
    }

    @Test
    public void collect_should_only_return_available_components() throws Exception {
        String applicationId = "applicationId";
        Set<String> applicationIds = newHashSet(applicationId, randomAlphabetic(6), randomAlphabetic(6));

        given(collector.collect()).willReturn(applicationIds);
        given(assembler.assemble(applicationId)).willAnswer(new Answer<Application>() {

            @Override
            public Application answer(InvocationOnMock invocation) throws Throwable {
                return applicationId.equals(invocation.getArguments()[0]) ? new Application() : null;
            }
        });

        Set<Application> components = new ApplicationCollector(collector, assembler).collect();
        assertThat(components).isNotNull().hasSize(1);

        verify(assembler, times(applicationIds.size())).assemble(anyString());
    }
}
