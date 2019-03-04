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

import com.github.ingogriebsch.bricks.dashboard.service.ApplicationService;
import com.github.ingogriebsch.bricks.dashboard.service.ComponentService;
import com.github.ingogriebsch.bricks.dashboard.web.Breadcrumb.Entry;
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

    @NonNull
    private final ApplicationService applicationService;
    @NonNull
    private final ComponentService componentService;

    @GetMapping(path = "/applications/{applicationId}/components/{componentId}/overview", produces = MediaType.TEXT_HTML_VALUE)
    public String overview(@PathVariable String applicationId, @PathVariable String componentId, @NonNull Model model)
        throws Exception {
        Application application = getApplication(applicationId);
        model.addAttribute("application", application);

        Component component = getComponent(applicationId, componentId);
        model.addAttribute("component", component);

        Breadcrumb breadcrumb = breadcrumb(application, component);
        model.addAttribute("breadcrumb", breadcrumb);

        return "/component/overview";
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

    private Breadcrumb breadcrumb(Application application, Component component) {
        Entry applicationsEntry = Entry.builder().name("Applications").href("/applications").build();
        Entry applicationEntry =
            Entry.builder().name(application.getName()).href(applicationsEntry.getHref() + "/" + application.getId()).build();
        Entry componentsEntry = Entry.builder().name("Components").href(applicationEntry.getHref() + "/components").build();
        Entry componentEntry =
            Entry.builder().name(component.getName()).href(componentsEntry.getHref() + "/" + component.getId()).build();

        Breadcrumb breadcrumb = Breadcrumb.builder().entry(applicationsEntry).entry(applicationEntry).entry(componentsEntry)
            .entry(componentEntry).build();
        return breadcrumb;
    }
}
