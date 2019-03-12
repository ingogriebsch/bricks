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
package com.github.ingogriebsch.bricks.dashboard.web;

import static java.util.stream.Collectors.toSet;

import static com.github.ingogriebsch.bricks.dashboard.web.IndexController.PATH_APPLICATIONS;
import static com.github.ingogriebsch.bricks.dashboard.web.IndexController.PATH_ROOT;
import static com.github.ingogriebsch.bricks.dashboard.web.IndexController.buildPath;
import static com.google.common.collect.Sets.newHashSet;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.http.MediaType.TEXT_HTML_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Set;

import com.github.ingogriebsch.bricks.dashboard.DashboardProperties;
import com.github.ingogriebsch.bricks.dashboard.service.ApplicationService;
import com.github.ingogriebsch.bricks.model.Application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@ExtendWith(SpringExtension.class)
@WebMvcTest(IndexController.class)
public class IndexControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplicationService applicationService;
    @MockBean
    private DashboardProperties dashboardProperties;

    @Test
    public void index_should_return_matching_redirect_if_isRedirectToApplicationViewIfOnlyOneAvailable_is_false()
        throws Exception {
        given(dashboardProperties.isRedirectToApplicationViewIfOnlyOneAvailable()).willReturn(false);

        ResultActions resultActions = mockMvc.perform(get(PATH_ROOT).accept(TEXT_HTML_VALUE));
        resultActions.andExpect(status().is3xxRedirection());
        resultActions.andExpect(redirectedUrl(PATH_APPLICATIONS));

        verify(dashboardProperties).isRedirectToApplicationViewIfOnlyOneAvailable();
        verifyNoMoreInteractions(dashboardProperties);
        verifyZeroInteractions(applicationService);
    }

    @Test
    public void
        index_should_return_matching_redirect_if_isRedirectToApplicationViewIfOnlyOneAvailable_is_true_and_no_applications_are_available()
            throws Exception {
        given(dashboardProperties.isRedirectToApplicationViewIfOnlyOneAvailable()).willReturn(true);
        given(applicationService.findAll()).willReturn(applications());

        ResultActions resultActions = mockMvc.perform(get(PATH_ROOT).accept(TEXT_HTML_VALUE));
        resultActions.andExpect(status().is3xxRedirection());
        resultActions.andExpect(redirectedUrl(PATH_APPLICATIONS));

        verify(dashboardProperties, times(1)).isRedirectToApplicationViewIfOnlyOneAvailable();
        verifyNoMoreInteractions(dashboardProperties);
        verify(applicationService, times(1)).findAll();
        verifyNoMoreInteractions(applicationService);
    }

    @Test
    public void
        index_should_return_matching_redirect_if_isRedirectToApplicationViewIfOnlyOneAvailable_is_true_and_several_applications_are_available()
            throws Exception {
        given(dashboardProperties.isRedirectToApplicationViewIfOnlyOneAvailable()).willReturn(true);
        given(applicationService.findAll()).willReturn(applications("id1", "id2", "id3"));

        ResultActions resultActions = mockMvc.perform(get(PATH_ROOT).accept(TEXT_HTML_VALUE));
        resultActions.andExpect(status().is3xxRedirection());
        resultActions.andExpect(redirectedUrl(PATH_APPLICATIONS));

        verify(dashboardProperties, times(1)).isRedirectToApplicationViewIfOnlyOneAvailable();
        verifyNoMoreInteractions(dashboardProperties);
        verify(applicationService, times(1)).findAll();
        verifyNoMoreInteractions(applicationService);
    }

    @Test
    public void
        index_should_return_matching_redirect_if_isRedirectToApplicationViewIfOnlyOneAvailable_is_true_and_one_applications_is_available()
            throws Exception {
        String applicationId = "id1";
        given(applicationService.findAll()).willReturn(applications(applicationId));
        given(dashboardProperties.isRedirectToApplicationViewIfOnlyOneAvailable()).willReturn(true);

        ResultActions resultActions = mockMvc.perform(get(PATH_ROOT).accept(TEXT_HTML_VALUE));
        resultActions.andExpect(status().is3xxRedirection());
        resultActions.andExpect(redirectedUrl(buildPath(PATH_APPLICATIONS, applicationId)));

        verify(dashboardProperties, times(1)).isRedirectToApplicationViewIfOnlyOneAvailable();
        verifyNoMoreInteractions(dashboardProperties);
        verify(applicationService, times(1)).findAll();
        verifyNoMoreInteractions(applicationService);
    }

    private static Set<Application> applications(String... ids) {
        return newHashSet(ids).stream().map(id -> application(id)).collect(toSet());
    }

    private static Application application(String id) {
        Application application = new Application();
        application.setId(id);
        return application;
    }
}
