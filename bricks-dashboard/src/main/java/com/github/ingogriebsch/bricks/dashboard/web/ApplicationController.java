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

import static com.github.ingogriebsch.bricks.dashboard.web.BreadcrumbFactory.create;
import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

import java.util.Set;

import com.github.ingogriebsch.bricks.dashboard.service.ApplicationService;
import com.github.ingogriebsch.bricks.dashboard.service.ComponentService;
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
public class ApplicationController {

    private static final String PAGE_APPLICATION_OVERVIEW = "/application/overview";
    private static final String PAGE_APPLICATION_COMPONENTS = "/application/components";
    private static final String PAGE_APPLICATION_DEPENDENCIES = "/application/dependencies";

    static final String PATH_APPLICATION = "/applications/{applicationId}";
    static final String PATH_APPLICATION_OVERVIEW = PATH_APPLICATION + "/overview";
    static final String PATH_APPLICATION_COMPONENTS = PATH_APPLICATION + "/components";
    static final String PATH_APPLICATION_DEPENDENCIES = PATH_APPLICATION + "/dependencies";

    @NonNull
    private final ApplicationService applicationService;
    @NonNull
    private final ComponentService componentService;

    @GetMapping(path = { PATH_APPLICATION, PATH_APPLICATION_OVERVIEW }, produces = TEXT_HTML_VALUE)
    public String overview(@PathVariable String applicationId, @NonNull Model model) throws Exception {
        Application application = application(applicationId);
        model.addAttribute("application", application);

        Breadcrumb breadcrumb = create(PATH_APPLICATION_OVERVIEW, application);
        model.addAttribute("breadcrumb", breadcrumb);

        return PAGE_APPLICATION_OVERVIEW;
    }

    @GetMapping(path = PATH_APPLICATION_COMPONENTS, produces = TEXT_HTML_VALUE)
    public String components(@PathVariable String applicationId, @NonNull Model model) throws Exception {
        Application application = application(applicationId);
        model.addAttribute("application", application);

        Set<Component> components = componentService.findAll(applicationId);
        model.addAttribute("components", components);

        Breadcrumb breadcrumb = create(PATH_APPLICATION_COMPONENTS, application);
        model.addAttribute("breadcrumb", breadcrumb);

        return PAGE_APPLICATION_COMPONENTS;
    }

    @GetMapping(path = PATH_APPLICATION_DEPENDENCIES, produces = TEXT_HTML_VALUE)
    public String dependencies(@PathVariable String applicationId, @NonNull Model model) throws Exception {
        Application application = application(applicationId);
        model.addAttribute("application", application);

        Breadcrumb breadcrumb = create(PATH_APPLICATION_DEPENDENCIES, application);
        model.addAttribute("breadcrumb", breadcrumb);

        return PAGE_APPLICATION_DEPENDENCIES;
    }

    private Application application(String applicationId) throws Exception {
        Application application = applicationService.findOne(applicationId).orElseThrow(() -> new IllegalStateException(
            format("Weird things happen! Application with id '%s' is not available!", applicationId)));
        return application;
    }
}
