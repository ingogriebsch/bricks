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

import static com.github.ingogriebsch.bricks.dashboard.web.BreadcrumbFactory.create;

import com.github.ingogriebsch.bricks.dashboard.service.ApplicationService;
import com.github.ingogriebsch.bricks.dashboard.service.ComponentService;
import com.github.ingogriebsch.bricks.model.Application;
import com.github.ingogriebsch.bricks.model.Component;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ComponentController {

    static final String PATH_COMPONENT = "/applications/{applicationId}/components/{componentId}";
    static final String PATH_COMPONENT_OVERVIEW = PATH_COMPONENT + "/overview";
    static final String MODEL_ATTRIBUTE_APPLICATION = "application";
    static final String MODEL_ATTRIBUTE_COMPONENT = "component";
    static final String MODEL_ATTRIBUTE_BREADCRUMB = "breadcrumb";
    static final String MODEL_ATTRIBUTE_VIEW = "view";
    static final String PAGE_COMPONENT_OVERVIEW = "/component/overview";

    @NonNull
    private final ApplicationService applicationService;
    @NonNull
    private final ComponentService componentService;

    @GetMapping(path = { PATH_COMPONENT, PATH_COMPONENT_OVERVIEW }, produces = MediaType.TEXT_HTML_VALUE)
    public String overview(@PathVariable String applicationId, @PathVariable String componentId, @NonNull Model model)
        throws Exception {
        Application application = getApplication(applicationId);
        model.addAttribute(MODEL_ATTRIBUTE_APPLICATION, application);

        Component component = getComponent(applicationId, componentId);
        model.addAttribute(MODEL_ATTRIBUTE_COMPONENT, component);

        Breadcrumb breadcrumb = create(PATH_COMPONENT_OVERVIEW, application, component);
        model.addAttribute(MODEL_ATTRIBUTE_BREADCRUMB, breadcrumb);

        model.addAttribute(MODEL_ATTRIBUTE_VIEW, "overview");
        return PAGE_COMPONENT_OVERVIEW;
    }

    private Application getApplication(String applicationId) throws Exception {
        Application application = applicationService.findOne(applicationId).orElseThrow(() -> new IllegalStateException(
            String.format("Weird things happen! Application with id '%s' is not available!", applicationId)));
        return application;
    }

    private Component getComponent(String applicationId, String componentId) throws Exception {
        Component component = componentService.findOne(applicationId, componentId)
            .orElseThrow(() -> new IllegalStateException(
                String.format("Weird things happen! Component with id '%s' for application with id '%s' is not available!",
                    componentId, applicationId)));
        return component;
    }
}
