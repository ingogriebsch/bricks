package com.github.ingogriebsch.bricks.assemble.reader.common;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.validation.ValidationException;

import com.github.ingogriebsch.bricks.assemble.converter.ApplicationConverter;
import com.github.ingogriebsch.bricks.assemble.loader.ResourceLoader;
import com.github.ingogriebsch.bricks.model.Application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SimpleApplicationReaderTest {

    @Mock
    private ResourceLoader resourceLoader;

    @Mock
    private ApplicationConverter applicationConverter;

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_input_is_null() {
        new SimpleApplicationReader(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_first_parameter_is_null() {
        new SimpleApplicationReader(null, applicationConverter);
    }

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_second_parameter_is_null() {
        new SimpleApplicationReader(resourceLoader, null);
    }

    @Test
    public void creation_should_succeed_if_input_is_given() {
        new SimpleApplicationReader(resourceLoader, applicationConverter);
    }

    @Test(expected = NullPointerException.class)
    public void read_should_throw_exception_if_input_is_null() throws Exception {
        new SimpleApplicationReader(resourceLoader, applicationConverter).read(null);
    }

    @Test
    public void read_should_return_null_if_resource_is_not_available() throws Exception {
        String id = randomAlphabetic(10);
        given(resourceLoader.load(id)).willReturn(null);

        SimpleApplicationReader simpleApplicationReader = new SimpleApplicationReader(resourceLoader, applicationConverter);
        assertThat(simpleApplicationReader.read(id)).isNull();

        verify(applicationConverter, times(0)).convert(any());
    }

    @Test
    public void read_should_return_application_if_resource_is_available() throws Exception {
        String id = randomAlphabetic(10);
        InputStream stream = new ByteArrayInputStream("{}".getBytes());

        given(resourceLoader.load(id)).willReturn(stream);
        given(applicationConverter.convert(stream)).willReturn(new Application());

        SimpleApplicationReader simpleApplicationReader =
            new SimpleApplicationReader(resourceLoader, applicationConverter, false);
        assertThat(simpleApplicationReader.read(id)).isNotNull();
    }

    @Test(expected = ValidationException.class)
    public void read_should_throw_exception_if_application_is_not_valid() throws Exception {
        String id = randomAlphabetic(10);
        InputStream stream = new ByteArrayInputStream("{}".getBytes());

        given(resourceLoader.load(id)).willReturn(stream);
        given(applicationConverter.convert(stream)).willReturn(new Application());

        new SimpleApplicationReader(resourceLoader, applicationConverter, true).read(id);
    }
}
