package com.github.ingogriebsch.bricks.assemble.assembler;

import java.io.InputStream;

import com.github.ingogriebsch.bricks.assemble.collector.ComponentCollector;
import com.github.ingogriebsch.bricks.assemble.converter.ApplicationConverter;
import com.github.ingogriebsch.bricks.assemble.reader.ApplicationReader;
import com.github.ingogriebsch.bricks.assemble.utils.validate.Validator;
import com.github.ingogriebsch.bricks.model.Application;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApplicationAssembler {

    private final Validator validator = new Validator();

    @NonNull
    private final ApplicationReader applicationReader;
    @NonNull
    private final ApplicationConverter applicationConverter;
    @NonNull
    private final ComponentCollector componentCollector;

    public Application assemble(@NonNull String id) throws Exception {
        Application application;
        try (InputStream source = applicationReader.read(id)) {
            application = applicationConverter.convert(source);
        }

        application.setComponents(componentCollector.collect(id));
        validator.validate(application);
        return application;
    }

}
