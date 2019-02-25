package com.github.ingogriebsch.bricks.dashboard.service;

import static com.google.common.collect.MoreCollectors.toOptional;

import java.util.Optional;
import java.util.Set;

import com.github.ingogriebsch.bricks.assemble.collector.ApplicationCollector;
import com.github.ingogriebsch.bricks.model.Application;

import org.springframework.stereotype.Component;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationCollector applicationCollector;

    public Set<Application> findAll() throws Exception {
        return applicationCollector.collect();
    }

    public Optional<Application> findOne(@NonNull String id) throws Exception {
        return findAll().stream().filter(a -> id.equals(a.getId())).collect(toOptional());
    }
}
