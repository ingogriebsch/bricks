package com.github.ingogriebsch.bricks.assemble.collector.common;

import java.util.Set;

import com.github.ingogriebsch.bricks.assemble.collector.ApplicationIdCollector;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StaticApplicationIdCollector implements ApplicationIdCollector {

    @NonNull
    private final Set<String> applicationIds;

    @Override
    public Set<String> collect() {
        return applicationIds;
    }

}
