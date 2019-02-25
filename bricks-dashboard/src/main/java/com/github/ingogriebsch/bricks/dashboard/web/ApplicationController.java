package com.github.ingogriebsch.bricks.dashboard.web;

import static java.lang.String.format;

import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

import com.github.ingogriebsch.bricks.dashboard.service.ApplicationService;
import com.github.ingogriebsch.bricks.model.Application;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
