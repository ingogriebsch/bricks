package com.github.ingogriebsch.bricks.assemble.converter.json;

import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ingogriebsch.bricks.model.Application;

import org.junit.Test;

public class Json2ApplicationConverterTest {

    @Test(expected = NullPointerException.class)
    public void convert_should_throw_exception_if_input_is_null() throws Exception {
        new Json2ApplicationConverter().convert(null);
    }

    @Test(expected = IOException.class)
    public void convert_should_throw_exception_if_input_is_not_legal() throws Exception {
        try (InputStream is = new ByteArrayInputStream("test".getBytes())) {
            new Json2ApplicationConverter().convert(is);
        }
    }

    @Test
    public void convert_should_convert_empty_application_to_matching_output() throws Exception {
        Application input = new Application();

        Application output;
        try (InputStream is = new ByteArrayInputStream(new ObjectMapper().writeValueAsBytes(input))) {
            output = new Json2ApplicationConverter().convert(is);
        }

        assertThat(output).isNotNull().isEqualTo(input);
    }

    @Test
    public void convert_should_convert_filled_application_to_matching_output() throws Exception {
        Application input = new Application("id", "name", "description", "version", newHashSet(), newHashSet());

        Application output;
        try (InputStream is = new ByteArrayInputStream(new ObjectMapper().writeValueAsBytes(input))) {
            output = new Json2ApplicationConverter().convert(is);
        }

        assertThat(output).isNotNull().isEqualTo(input);
    }
}
