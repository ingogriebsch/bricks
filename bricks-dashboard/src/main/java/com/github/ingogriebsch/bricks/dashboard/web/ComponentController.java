package com.github.ingogriebsch.bricks.dashboard.web;

import static java.lang.String.format;

import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

import com.github.ingogriebsch.bricks.dashboard.service.ComponentService;
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
    private final ComponentService componentService;

    @GetMapping(path = "/applications/{applicationId}/components", produces = TEXT_HTML_VALUE)
    public String all(@PathVariable String applicationId, @NonNull Model model) throws Exception {
        model.addAttribute("applicationId", applicationId);
        model.addAttribute("components", componentService.findAll(applicationId));
        return "/component/all";
    }

    @GetMapping(path = "/applications/{applicationId}/components/{componentId}", produces = TEXT_HTML_VALUE)
    public String one(@PathVariable String applicationId, @PathVariable String componentId, @NonNull Model model)
        throws Exception {
        Component component = componentService.findOne(applicationId, componentId)
            .orElseThrow(() -> new IllegalStateException(
                format("Weird things happen! Component with id '%s' for application with id '%s' is not available!", componentId,
                    applicationId)));

        model.addAttribute("applicationId", applicationId);
        model.addAttribute("component", component);
        return "/component/one";
    }
}
