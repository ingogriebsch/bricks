package com.github.ingogriebsch.bricks.assemble.converter.json;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ingogriebsch.bricks.assemble.converter.ComponentConverter;
import com.github.ingogriebsch.bricks.model.Component;

import lombok.NonNull;

public class Json2ComponentConverter implements ComponentConverter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Component convert(@NonNull InputStream source) throws IOException {
        return objectMapper.readValue(source, Component.class);
    }

}
