package com.github.ingogriebsch.bricks.assemble.reader.common;

import java.io.InputStream;

import com.github.ingogriebsch.bricks.assemble.converter.ApplicationConverter;
import com.github.ingogriebsch.bricks.assemble.loader.ResourceLoader;
import com.github.ingogriebsch.bricks.assemble.reader.ApplicationReader;
import com.github.ingogriebsch.bricks.assemble.utils.validate.Validator;
import com.github.ingogriebsch.bricks.model.Application;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimpleApplicationReader implements ApplicationReader {

    private static final Validator validator = new Validator();

    @NonNull
    private final ResourceLoader resourceLoader;
    @NonNull
    private final ApplicationConverter applicationConverter;
    private final boolean validate;

    public SimpleApplicationReader(ResourceLoader resourceLoader, ApplicationConverter applicationConverter) {
        this(resourceLoader, applicationConverter, true);
    }

    @Override
    public Application read(@NonNull String id) throws Exception {
        Application application = null;
        try (InputStream resource = resourceLoader.load(id)) {
            if (resource != null) {
                application = applicationConverter.convert(resource);
            }
        }

        if (application != null && validate) {
            validator.validate(application);
        }
        return application;
    }
}
