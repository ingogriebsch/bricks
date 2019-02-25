package com.github.ingogriebsch.bricks.assemble.collector;

import java.util.HashSet;
import java.util.Set;

import com.github.ingogriebsch.bricks.assemble.assembler.ApplicationAssembler;
import com.github.ingogriebsch.bricks.model.Application;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApplicationCollector {

    @NonNull
    private final ApplicationIdCollector applicationIdCollector;
    @NonNull
    private final ApplicationAssembler applicationAssembler;

    public Set<Application> collect() throws Exception {
        Set<String> applicationIds = applicationIdCollector.collect();

        Set<Application> result = new HashSet<>(applicationIds.size());
        for (String id : applicationIds) {
            Application application = applicationAssembler.assemble(id);
            if (application != null) {
                result.add(application);
            }
        }
        return result;
    }

}
