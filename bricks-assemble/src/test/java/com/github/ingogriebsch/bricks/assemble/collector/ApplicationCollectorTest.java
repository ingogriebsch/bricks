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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Set;

import com.github.ingogriebsch.bricks.assemble.reader.ApplicationReader;
import com.github.ingogriebsch.bricks.assemble.reader.ApplicationReaderFactory;
import com.github.ingogriebsch.bricks.model.Application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
public class ApplicationCollectorTest {

    @Mock
    private ApplicationIdCollector applicationIdCollector;
    @Mock
    private ApplicationReaderFactory applicationReaderfactory;
    @Mock
    private ApplicationReader applicationReader;

    @Test
    public void creation_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> {
            new ApplicationCollector(null, null);
        });
    }

    @Test
    public void creation_should_throw_exception_if_collector_is_null() {
        assertThrows(NullPointerException.class, () -> {
            new ApplicationCollector(null, applicationReaderfactory);
        });
    }

    @Test
    public void creation_should_throw_exception_if_assembler_is_null() {
        assertThrows(NullPointerException.class, () -> {
            new ApplicationCollector(applicationIdCollector, null);
        });
    }

    @Test
    public void collect_throws_exception_if_collector_throws_exception() throws Exception {
        given(applicationIdCollector.collect()).willThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            new ApplicationCollector(applicationIdCollector, applicationReaderfactory).collect();
        });
    }

    @Test
    public void collect_throws_exception_if_factory_throws_exception() throws Exception {
        given(applicationIdCollector.collect()).willReturn(newHashSet(randomAlphabetic(6)));
        given(applicationReaderfactory.create(anyString())).willThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            new ApplicationCollector(applicationIdCollector, applicationReaderfactory).collect();
        });
    }

    @Test
    public void collect_throws_exception_if_factory_does_not_create_a_reader() throws Exception {
        given(applicationIdCollector.collect()).willReturn(newHashSet(randomAlphabetic(6)));
        given(applicationReaderfactory.create(anyString())).willReturn(null);

        assertThrows(IllegalStateException.class, () -> {
            new ApplicationCollector(applicationIdCollector, applicationReaderfactory).collect();
        });
    }

    @Test
    public void collect_should_call_collector() throws Exception {
        given(applicationIdCollector.collect()).willReturn(newHashSet(randomAlphabetic(6)));
        given(applicationReaderfactory.create(anyString())).willReturn(applicationReader);

        new ApplicationCollector(applicationIdCollector, applicationReaderfactory).collect();

        verify(applicationIdCollector).collect();
    }

    @Test
    public void collect_should_call_reader_based_on_the_collector_output() throws Exception {
        String applicationId = "applicationId";
        Set<String> applicationIds = newHashSet(applicationId, randomAlphabetic(6), randomAlphabetic(6));

        given(applicationIdCollector.collect()).willReturn(applicationIds);
        given(applicationReaderfactory.create(anyString())).willReturn(applicationReader);
        given(applicationReader.read(anyString())).willAnswer(new Answer<Application>() {

            @Override
            public Application answer(InvocationOnMock invocation) throws Throwable {
                return applicationId.equals(invocation.getArguments()[0]) ? new Application() : null;
            }
        });

        new ApplicationCollector(applicationIdCollector, applicationReaderfactory).collect();

        verify(applicationIdCollector).collect();
        verify(applicationReader, times(applicationIds.size())).read(anyString());
    }

    @Test
    public void collect_should_only_return_available_applications() throws Exception {
        String applicationId = "applicationId";
        Set<String> applicationIds = newHashSet(applicationId, randomAlphabetic(6), randomAlphabetic(6));

        given(applicationIdCollector.collect()).willReturn(applicationIds);
        given(applicationReaderfactory.create(anyString())).willReturn(applicationReader);
        given(applicationReader.read(anyString())).willAnswer(new Answer<Application>() {

            @Override
            public Application answer(InvocationOnMock invocation) throws Throwable {
                return applicationId.equals(invocation.getArguments()[0]) ? new Application() : null;
            }
        });

        Set<Application> applications = new ApplicationCollector(applicationIdCollector, applicationReaderfactory).collect();

        assertThat(applications).isNotNull().hasSize(1);
        verify(applicationReader, times(applicationIds.size())).read(anyString());
    }
}
