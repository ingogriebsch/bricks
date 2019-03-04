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
package com.github.ingogriebsch.bricks.assemble.collector;

import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Set;

import com.github.ingogriebsch.bricks.assemble.reader.ApplicationReader;
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
    private ApplicationReader reader;

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_input_is_null() {
        new ApplicationCollector(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_collector_is_null() {
        new ApplicationCollector(null, reader);
    }

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_assembler_is_null() {
        new ApplicationCollector(collector, null);
    }

    @Test(expected = RuntimeException.class)
    public void collect_throws_exception_if_collector_throws_exception() throws Exception {
        given(collector.collect()).willThrow(new RuntimeException());

        new ApplicationCollector(collector, reader).collect();
    }

    @Test(expected = RuntimeException.class)
    public void collect_throws_exception_if_assembler_throws_exception() throws Exception {
        given(collector.collect()).willReturn(newHashSet(randomAlphabetic(6)));
        given(reader.read(anyString())).willThrow(new RuntimeException());

        new ApplicationCollector(collector, reader).collect();
    }

    @Test
    public void collect_should_call_collector() throws Exception {
        given(collector.collect()).willReturn(newHashSet(randomAlphabetic(6)));
        given(reader.read(anyString())).willReturn(null);

        new ApplicationCollector(collector, reader).collect();

        verify(collector).collect();
    }

    @Test
    public void collect_should_call_assembler_based_on_the_collector_output() throws Exception {
        String applicationId = "applicationId";
        Set<String> applicationIds = newHashSet(applicationId, randomAlphabetic(6), randomAlphabetic(6));

        given(collector.collect()).willReturn(applicationIds);
        given(reader.read(anyString())).willAnswer(new Answer<Application>() {

            @Override
            public Application answer(InvocationOnMock invocation) throws Throwable {
                return applicationId.equals(invocation.getArguments()[0]) ? new Application() : null;
            }
        });

        new ApplicationCollector(collector, reader).collect();

        verify(collector).collect();
        verify(reader, times(applicationIds.size())).read(anyString());
    }

    @Test
    public void collect_should_only_return_available_components() throws Exception {
        String applicationId = "applicationId";
        Set<String> applicationIds = newHashSet(applicationId, randomAlphabetic(6), randomAlphabetic(6));

        given(collector.collect()).willReturn(applicationIds);
        given(reader.read(applicationId)).willAnswer(new Answer<Application>() {

            @Override
            public Application answer(InvocationOnMock invocation) throws Throwable {
                return applicationId.equals(invocation.getArguments()[0]) ? new Application() : null;
            }
        });

        Set<Application> components = new ApplicationCollector(collector, reader).collect();
        assertThat(components).isNotNull().hasSize(1);

        verify(reader, times(applicationIds.size())).read(anyString());
    }
}
