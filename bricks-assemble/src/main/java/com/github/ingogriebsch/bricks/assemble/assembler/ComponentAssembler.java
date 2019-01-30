package com.github.ingogriebsch.bricks.assemble.assembler;

import java.io.InputStream;

import com.github.ingogriebsch.bricks.assemble.converter.ComponentConverter;
import com.github.ingogriebsch.bricks.assemble.reader.ComponentReader;
import com.github.ingogriebsch.bricks.assemble.utils.validate.Validator;
import com.github.ingogriebsch.bricks.model.Component;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ComponentAssembler {

    private final Validator validator = new Validator();

    @NonNull
    private final ComponentReader componentReader;
    @NonNull
    private final ComponentConverter componentConverter;

    public Component assemble(@NonNull String id) throws Exception {
        Component component = null;
        try (InputStream source = componentReader.read(id)) {
            if (source != null) {
                component = componentConverter.convert(source);
            }
        }

        if (component != null) {
            validator.validate(component);
        }
        return component;
    }

}
