package com.github.ingogriebsch.bricks.dashboard.service;

import static com.google.common.collect.MoreCollectors.toOptional;
import static com.google.common.collect.Sets.newHashSet;

import java.util.Optional;
import java.util.Set;

import com.github.ingogriebsch.bricks.model.Application;
import com.github.ingogriebsch.bricks.model.Component;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Component
@RequiredArgsConstructor
public class ComponentService {

    private final ApplicationService applicationService;

    public Set<Component> findAll(@NonNull String applicationId) throws Exception {
        Optional<Application> optional = applicationService.findOne(applicationId);
        Set<Component> components = optional.isPresent() ? optional.get().getComponents() : null;
        return components != null ? components : newHashSet();
    }

    public Optional<Component> findOne(@NonNull String applicationId, @NonNull String id) throws Exception {
        return findAll(applicationId).stream().filter(a -> id.equals(a.getId())).collect(toOptional());
    }
}
