package com.github.ingogriebsch.bricks.assemble.loader.common;

import static java.nio.charset.Charset.forName;

import static org.apache.commons.io.IOUtils.toInputStream;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;

import org.junit.Test;

public class InputStreamResourceLoaderTest {

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_input_is_null() {
        new InputStreamResourceLoader(null);
    }

    @Test
    public void load_should_return_same_input_stream_as_given_during_creation() throws Exception {
        try (InputStream input = toInputStream(randomAlphabetic(64), forName("UTF-8"))) {
            InputStream output = new InputStreamResourceLoader(input).load(randomAlphabetic(12));
            assertThat(input).isSameAs(output);
        }
    }
}
