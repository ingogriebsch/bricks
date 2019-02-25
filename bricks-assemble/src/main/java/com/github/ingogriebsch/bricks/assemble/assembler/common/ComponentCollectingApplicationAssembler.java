package com.github.ingogriebsch.bricks.assemble.assembler.common;

import java.util.Set;

import com.github.ingogriebsch.bricks.assemble.assembler.ApplicationAssembler;
import com.github.ingogriebsch.bricks.assemble.collector.ComponentCollector;
import com.github.ingogriebsch.bricks.assemble.reader.ApplicationReader;
import com.github.ingogriebsch.bricks.assemble.utils.validate.Validator;
import com.github.ingogriebsch.bricks.model.Application;
import com.github.ingogriebsch.bricks.model.Component;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ComponentCollectingApplicationAssembler implements ApplicationAssembler {

    private static final Validator validator = new Validator();

    @NonNull
    private final ApplicationReader applicationReader;
    @NonNull
    private final ComponentCollector componentCollector;
    private final boolean validate;

    public ComponentCollectingApplicationAssembler(ApplicationReader applicationReader, ComponentCollector componentCollector) {
        this(applicationReader, componentCollector, true);
    }

    @Override
    public Application assemble(@NonNull String id) throws Exception {
        Application application = applicationReader.read(id);
        if (application == null) {
            return null;
        }

        Set<Component> components = componentCollector.collect(id);
        components = merge(components, application.getComponents());
        application.setComponents(components);

        if (validate) {
            validator.validate(application);
        }
        return application;
    }

    private static Set<Component> merge(Set<Component> collected, Set<Component> fromApplication) {
        return collected;
    }

}
