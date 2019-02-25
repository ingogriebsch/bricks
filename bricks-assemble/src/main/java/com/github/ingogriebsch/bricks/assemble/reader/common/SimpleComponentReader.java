package com.github.ingogriebsch.bricks.assemble.reader.common;

import java.io.InputStream;

import com.github.ingogriebsch.bricks.assemble.converter.ComponentConverter;
import com.github.ingogriebsch.bricks.assemble.loader.ResourceLoader;
import com.github.ingogriebsch.bricks.assemble.reader.ComponentReader;
import com.github.ingogriebsch.bricks.assemble.utils.validate.Validator;
import com.github.ingogriebsch.bricks.model.Component;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimpleComponentReader implements ComponentReader {

    private final static Validator validator = new Validator();

    @NonNull
    private final ResourceLoader resourceLoader;
    @NonNull
    private final ComponentConverter componentConverter;
    private final boolean validate;

    public SimpleComponentReader(ResourceLoader resourceLoader, ComponentConverter componentConverter) {
        this(resourceLoader, componentConverter, true);
    }

    @Override
    public Component read(@NonNull String id) throws Exception {
        Component component = null;
        try (InputStream resource = resourceLoader.load(id)) {
            if (resource != null) {
                component = componentConverter.convert(resource);
            }
        }

        if (component != null && validate) {
            validator.validate(component);
        }
        return component;
    }
}
