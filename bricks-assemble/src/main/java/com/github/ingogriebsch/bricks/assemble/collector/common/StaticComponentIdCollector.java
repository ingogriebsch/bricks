package com.github.ingogriebsch.bricks.assemble.collector.common;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.unmodifiableSet;

import java.util.Set;

import com.github.ingogriebsch.bricks.assemble.collector.ComponentIdCollector;

import lombok.NonNull;

public class StaticComponentIdCollector implements ComponentIdCollector {

    @NonNull
    private final Set<String> componentIds;

    public StaticComponentIdCollector(Set<String> componentIds) {
        this.componentIds = unmodifiableSet(newHashSet(componentIds));
    }

    @Override
    public Set<String> collect(@NonNull String applicationId) {
        return componentIds;
    }

}
