package com.github.ingogriebsch.bricks.assemble.collector.common;

import java.util.Map;
import java.util.Set;

import com.github.ingogriebsch.bricks.assemble.collector.ComponentIdCollector;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StaticComponentIdCollector implements ComponentIdCollector {

    @NonNull
    private final Map<String, Set<String>> componentIds;

    @Override
    public Set<String> collect(@NonNull String applicationId) {
        return componentIds.get(applicationId);
    }

}
