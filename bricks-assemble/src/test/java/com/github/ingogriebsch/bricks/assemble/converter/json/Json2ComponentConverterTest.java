package com.github.ingogriebsch.bricks.assemble.converter.json;

import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ingogriebsch.bricks.model.Administration;
import com.github.ingogriebsch.bricks.model.Communication;
import com.github.ingogriebsch.bricks.model.Component;
import com.github.ingogriebsch.bricks.model.Ecosystem;
import com.github.ingogriebsch.bricks.model.MemoryFootprint;
import com.github.ingogriebsch.bricks.model.Monitoring;
import com.github.ingogriebsch.bricks.model.Responsible;
import com.github.ingogriebsch.bricks.model.Storage;

import org.junit.Test;

public class Json2ComponentConverterTest {

    @Test(expected = NullPointerException.class)
    public void convert_should_throw_exception_if_input_is_null() throws Exception {
        new Json2ComponentConverter().convert(null);
    }

    @Test(expected = IOException.class)
    public void convert_should_throw_exception_if_input_is_not_legal() throws Exception {
        try (InputStream is = new ByteArrayInputStream("test".getBytes())) {
            new Json2ComponentConverter().convert(is);
        }
    }

    @Test
    public void convert_should_convert_empty_component_to_matching_output() throws Exception {
        Component input = new Component();

        Component output;
        try (InputStream is = new ByteArrayInputStream(new ObjectMapper().writeValueAsBytes(input))) {
            output = new Json2ComponentConverter().convert(is);
        }

        assertThat(output).isNotNull().isEqualTo(input);
    }

    @Test
    public void convert_should_convert_filled_component_to_matching_output() throws Exception {
        Component input = new Component("id", "name", "description", "version", "layer", newHashSet("category"), false, false,
            newHashSet("Spring Boot"), newHashSet("Maven"), newHashSet(new Ecosystem("jvm", "Java", "1.8")),
            newHashSet(new Storage("id", "Database", "Postgres", true)), new MemoryFootprint(),
            new Administration("type", "path", "authorizationType"),
            newHashSet(new Responsible("Team Sirius", "sirius@google.com")), new Communication(), new Monitoring());

        Component output;
        try (InputStream is =
            new ByteArrayInputStream(new ObjectMapper().configure(FAIL_ON_EMPTY_BEANS, false).writeValueAsBytes(input))) {
            output = new Json2ComponentConverter().convert(is);
        }

        assertThat(output).isNotNull().isEqualTo(input);
    }
}
