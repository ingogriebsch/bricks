package com.github.ingogriebsch.bricks.assemble.collector;

import java.util.HashSet;
import java.util.Set;

import com.github.ingogriebsch.bricks.assemble.assembler.ComponentAssembler;
import com.github.ingogriebsch.bricks.model.Component;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ComponentCollector {

    @NonNull
    private final ComponentIdCollector componentIdCollector;
    @NonNull
    private final ComponentAssembler componentAssembler;

    public Set<Component> collect(@NonNull String applicationId) throws Exception {
        Set<String> componentIds = componentIdCollector.collect(applicationId);

        Set<Component> result = new HashSet<>(componentIds.size());
        for (String id : componentIds) {
            Component component = componentAssembler.assemble(id);
            if (component != null) {
                result.add(component);
            }
        }
        return result;
    }

}
