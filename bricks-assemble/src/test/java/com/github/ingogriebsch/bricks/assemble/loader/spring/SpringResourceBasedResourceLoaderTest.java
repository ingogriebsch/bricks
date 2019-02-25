package com.github.ingogriebsch.bricks.assemble.loader.spring;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.DescriptiveResource;
import org.springframework.core.io.ResourceLoader;

@RunWith(MockitoJUnitRunner.class)
public class SpringResourceBasedResourceLoaderTest {

    @Mock
    private ResourceLoader resourceLoader;

    @Mock
    private ResourceLocationProvider resourceLocationProvider;

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_input_is_null() {
        new SpringResourceBasedResourceLoader(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_first_parameter_is_null() {
        new SpringResourceBasedResourceLoader(null, resourceLocationProvider);
    }

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_second_parameter_is_null() {
        new SpringResourceBasedResourceLoader(resourceLoader, null);
    }

    @Test
    public void creation_should_succeed_if_all_input_parameters_are_given() {
        new SpringResourceBasedResourceLoader(resourceLoader, resourceLocationProvider);
    }

    @Test(expected = NullPointerException.class)
    public void load_should_throw_exception_if_input_is_null() throws IOException {
        new SpringResourceBasedResourceLoader(resourceLoader, resourceLocationProvider).load(null);
    }

    @Test
    public void load_should_return_null_if_resource_does_not_exist() throws IOException {
        String id = randomAlphabetic(8);
        given(resourceLocationProvider.getLocation(id)).willReturn(id);
        given(resourceLoader.getResource(id)).willReturn(new DescriptiveResource(id));

        SpringResourceBasedResourceLoader springResourceBasedResourceLoader =
            new SpringResourceBasedResourceLoader(resourceLoader, resourceLocationProvider);
        assertThat(springResourceBasedResourceLoader.load(id)).isNull();
    }

    @Test
    public void load_should_return_stream_if_resource_does_exist() throws IOException {
        String id = randomAlphabetic(8);
        given(resourceLocationProvider.getLocation(id)).willReturn(id);
        given(resourceLoader.getResource(id)).willReturn(new ByteArrayResource(random(128).getBytes(), id));

        SpringResourceBasedResourceLoader springResourceBasedResourceLoader =
            new SpringResourceBasedResourceLoader(resourceLoader, resourceLocationProvider);

        try (InputStream stream = springResourceBasedResourceLoader.load(id)) {
            assertThat(stream).isNotNull();
        }
    }
}
