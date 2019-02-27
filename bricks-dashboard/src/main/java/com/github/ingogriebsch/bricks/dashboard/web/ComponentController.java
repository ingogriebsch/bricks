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

import static java.lang.String.format;

import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

import java.util.Set;

import com.github.ingogriebsch.bricks.dashboard.facility.ApplicationFacility;
import com.github.ingogriebsch.bricks.dashboard.service.ApplicationService;
import com.github.ingogriebsch.bricks.dashboard.web.Breadcrumb.Entry;
import com.github.ingogriebsch.bricks.model.Application;
import com.github.ingogriebsch.bricks.model.Component;

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
    private final ApplicationFacility applicationFacility;

    @GetMapping(path = "/applications/{applicationId}/components", produces = TEXT_HTML_VALUE)
    public String all(@PathVariable String applicationId, @NonNull Model model) throws Exception {
        Application application = applicationService.findOne(applicationId).orElseThrow(() -> new IllegalStateException(
            format("Weird things happen! Application with id '%s' is not available!", applicationId)));
        model.addAttribute("application", application);

        Set<Component> components = applicationFacility.getComponents(application);
        model.addAttribute("components", components);

        Breadcrumb breadcrumb = Breadcrumb.builder().entry(Entry.builder().name("Applications").href("/applications").build())
            .entry(Entry.builder().name(application.getName()).href("/applications/" + applicationId).build())
            .entry(Entry.builder().name("Components").href("/applications/" + applicationId + "/components").build()).build();
        model.addAttribute("breadcrumb", breadcrumb);

        return "/component/all";
    }

    @GetMapping(path = "/applications/{applicationId}/components/{componentId}", produces = TEXT_HTML_VALUE)
    public String one(@PathVariable String applicationId, @PathVariable String componentId, @NonNull Model model)
        throws Exception {
        Application application = applicationService.findOne(applicationId).orElseThrow(() -> new IllegalStateException(
            format("Weird things happen! Application with id '%s' is not available!", applicationId)));
        model.addAttribute("application", application);

        Component component = applicationFacility.getComponent(application, componentId)
            .orElseThrow(() -> new IllegalStateException(
                format("Weird things happen! Component with id '%s' for application with id '%s' is not available!", componentId,
                    applicationId)));
        model.addAttribute("component", component);

        Breadcrumb breadcrumb = Breadcrumb.builder().entry(Entry.builder().name("Applications").href("/applications").build())
            .entry(Entry.builder().name(application.getName()).href("/applications/" + applicationId).build())
            .entry(Entry.builder().name("Components").href("/applications/" + applicationId + "/components").build()).entry(Entry
                .builder().name(component.getName()).href("/applications/" + applicationId + "/components" + componentId).build())
            .build();
        model.addAttribute("breadcrumb", breadcrumb);

        return "/component/one";
    }
}
