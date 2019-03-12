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
import static org.apache.commons.lang3.StringUtils.join;
import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

import java.util.Set;

import com.github.ingogriebsch.bricks.dashboard.DashboardProperties;
import com.github.ingogriebsch.bricks.dashboard.service.ApplicationService;
import com.github.ingogriebsch.bricks.model.Application;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private static final String PAGE_APPLICATIONS = "/index";

    static final String PATH_ROOT = "/";
    static final String PATH_APPLICATIONS = "/applications";

    @NonNull
    private final ApplicationService applicationService;
    @NonNull
    private final DashboardProperties dashboardProperties;

    @GetMapping(path = PATH_ROOT, produces = TEXT_HTML_VALUE)
    public String index(@NonNull Model model) throws Exception {
        String result = "redirect:" + PATH_APPLICATIONS;

        if (dashboardProperties.isRedirectToApplicationViewIfOnlyOneAvailable()) {
            Set<Application> applications = applicationService.findAll();
            if (applications.size() == 1) {
                result = buildPath(result, applications.iterator().next().getId());
            }
        }
        return result;
    }

    @GetMapping(path = PATH_APPLICATIONS, produces = TEXT_HTML_VALUE)
    public String applications(@NonNull Model model) throws Exception {
        Set<Application> applications = applicationService.findAll();
        model.addAttribute("applications", applications);

        Breadcrumb breadcrumb = create(PATH_APPLICATIONS);
        model.addAttribute("breadcrumb", breadcrumb);

        return PAGE_APPLICATIONS;
    }

    static String buildPath(String... parts) {
        return join(parts, "/");
    }
}
