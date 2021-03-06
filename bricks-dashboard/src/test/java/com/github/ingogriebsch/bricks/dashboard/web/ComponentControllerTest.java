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

import static java.util.Optional.empty;
import static java.util.Optional.of;

import static com.github.ingogriebsch.bricks.dashboard.web.ComponentController.MODEL_ATTRIBUTE_APPLICATION;
import static com.github.ingogriebsch.bricks.dashboard.web.ComponentController.MODEL_ATTRIBUTE_BREADCRUMB;
import static com.github.ingogriebsch.bricks.dashboard.web.ComponentController.MODEL_ATTRIBUTE_COMPONENT;
import static com.github.ingogriebsch.bricks.dashboard.web.ComponentController.MODEL_ATTRIBUTE_VIEW;
import static com.github.ingogriebsch.bricks.dashboard.web.ComponentController.PAGE_COMPONENT_OVERVIEW;
import static com.github.ingogriebsch.bricks.dashboard.web.ComponentController.PATH_COMPONENT_OVERVIEW;
import static com.github.ingogriebsch.bricks.dashboard.web.ExceptionHandler.PAGE_ERROR;
import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.http.MediaType.TEXT_HTML_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.github.ingogriebsch.bricks.dashboard.service.ApplicationService;
import com.github.ingogriebsch.bricks.dashboard.service.ComponentService;
import com.github.ingogriebsch.bricks.model.Application;
import com.github.ingogriebsch.bricks.model.Component;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComponentController.class)
public class ComponentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplicationService applicationService;
    @MockBean
    private ComponentService componentService;

    @Test
    public void overview_should_return_matching_view_and_model_if_component_is_available() throws Exception {
        String applicationId = "id1";
        String componentId = "id1";
        given(applicationService.findOne(applicationId)).willReturn(of(application(applicationId)));
        given(componentService.findOne(applicationId, componentId)).willReturn(of(component(componentId)));

        ResultActions resultActions =
            mockMvc.perform(get(PATH_COMPONENT_OVERVIEW, applicationId, componentId).accept(TEXT_HTML_VALUE));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(view().name(PAGE_COMPONENT_OVERVIEW));
        resultActions.andExpect(model().attributeExists(MODEL_ATTRIBUTE_APPLICATION, MODEL_ATTRIBUTE_COMPONENT,
            MODEL_ATTRIBUTE_BREADCRUMB, MODEL_ATTRIBUTE_VIEW));

        verify(applicationService).findOne(applicationId);
        verify(componentService).findOne(applicationId, componentId);
        verifyNoMoreInteractions(applicationService);
        verifyNoMoreInteractions(componentService);
    }

    @Test
    public void overview_should_return_error_view_if_component_is_not_available() throws Exception {
        String applicationId = "id1";
        String componentId = "id1";
        given(applicationService.findOne(applicationId)).willReturn(of(application(applicationId)));
        given(componentService.findOne(applicationId, componentId)).willReturn(empty());

        ResultActions resultActions =
            mockMvc.perform(get(PATH_COMPONENT_OVERVIEW, applicationId, componentId).accept(TEXT_HTML_VALUE));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(view().name(PAGE_ERROR));

        verify(applicationService).findOne(applicationId);
        verify(componentService).findOne(applicationId, componentId);
        verifyNoMoreInteractions(applicationService);
        verifyNoMoreInteractions(componentService);
    }

    private static Application application(String id) {
        Application application = new Application();
        application.setId(id);
        application.setName(randomAlphabetic(8));
        application.setVersion("1.0.0");
        application.setDescription(randomAlphabetic(12));
        return application;
    }

    private static Component component(String id) {
        Component component = new Component();
        component.setId(id);
        component.setName(randomAlphabetic(8));
        component.setVersion("1.0.0");
        component.setDescription(randomAlphabetic(12));
        component.setResponsibles(newHashSet());
        return component;
    }
}
