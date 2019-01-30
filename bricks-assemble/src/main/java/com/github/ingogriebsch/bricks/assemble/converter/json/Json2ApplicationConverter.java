package com.github.ingogriebsch.bricks.assemble.converter.json;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ingogriebsch.bricks.assemble.converter.ApplicationConverter;
import com.github.ingogriebsch.bricks.model.Application;

import lombok.NonNull;

public class Json2ApplicationConverter implements ApplicationConverter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Application convert(@NonNull InputStream source) throws IOException {
        return objectMapper.readValue(source, Application.class);
    }

}
