/*
 * Copyright © 2018 Ingo Griebsch (https://ingogriebsch.de/)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.ingogriebsch.bricks.dashboard.web;

import static java.lang.String.format;

import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.github.ingogriebsch.bricks.dashboard.service.ApplicationService;
import com.github.ingogriebsch.bricks.model.Application;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ApplicationController {

    @NonNull
    private final ApplicationService applicationService;

    @GetMapping(path = "/applications", produces = TEXT_HTML_VALUE)
    public String all(@NonNull Model model) throws Exception {
        model.addAttribute("applications", applicationService.findAll());
        return "/application/all";
    }

    @GetMapping(path = "/applications/{applicationId}", produces = TEXT_HTML_VALUE)
    public String one(@PathVariable String applicationId, @NonNull Model model) throws Exception {
        Application application = applicationService.findOne(applicationId).orElseThrow(() -> new IllegalStateException(
            format("Weird things happen! Application with id '%s' is not available!", applicationId)));

        model.addAttribute("application", application);
        return "/application/one";
    }
}
