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

import com.github.ingogriebsch.bricks.assemble.collector.ApplicationCollector;
import com.github.ingogriebsch.bricks.model.Application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {

    @Test
    public void creation_should_throw_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> {
            new ApplicationService(null);
        });
    }

    @Test
    public void findAll_should_return_empty_set_if_no_applications_are_available(@Mock ApplicationCollector collector)
        throws Exception {
        given(collector.collect()).willReturn(newHashSet());

        ApplicationService service = new ApplicationService(collector);
        assertThat(service.findAll()).isNotNull().isEmpty();
    }

    @Test
    public void findAll_should_return_the_applications_the_collector_returns(@Mock ApplicationCollector collector)
        throws Exception {
        Set<Application> applications =
            newHashSet(application(randomNumeric(8)), application(randomNumeric(8)), application(randomNumeric(8)));
        given(collector.collect()).willReturn(applications);

        ApplicationService service = new ApplicationService(collector);
        assertThat(service.findAll()).isNotNull().isEqualTo(applications);
    }

    @Test
    public void findOne_should_throw_exception_if_input_is_null(@Mock ApplicationCollector collector) {
        assertThrows(NullPointerException.class, () -> {
            new ApplicationService(collector).findOne(null);
        });
    }

    @Test
    public void findOne_should_return_empty_optional_if_application_is_not_available(@Mock ApplicationCollector collector)
        throws Exception {
        given(collector.collect()).willReturn(newHashSet());
        ApplicationService service = new ApplicationService(collector);

        Optional<Application> optional = service.findOne(randomNumeric(8));
        assertThat(optional).isNotNull().isEmpty();
    }

    @Test
    public void findOne_should_return_optional_containing_application_if_available(@Mock ApplicationCollector collector)
        throws Exception {
        Application application = application(randomNumeric(8));
        given(collector.collect()).willReturn(newHashSet(application));
        ApplicationService service = new ApplicationService(collector);

        Optional<Application> optional = service.findOne(application.getId());
        assertThat(optional).isNotNull().contains(application);
    }

    private static Application application(String id) {
        Application application = new Application();
        application.setId(id);
        return application;
    }
}
