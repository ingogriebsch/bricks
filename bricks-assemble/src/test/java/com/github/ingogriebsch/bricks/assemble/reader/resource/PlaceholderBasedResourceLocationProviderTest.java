package com.github.ingogriebsch.bricks.assemble.reader.resource;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PlaceholderBasedResourceLocationProviderTest {

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_input_is_null() {
        new PlaceholderBasedResourceLocationProvider(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_locationBase_is_null() {
        new PlaceholderBasedResourceLocationProvider(null, "id");
    }

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_idPlaceholderName_is_null() {
        new PlaceholderBasedResourceLocationProvider("classpath:${id}", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creation_should_throw_exception_if_given_placeholderName_is_not_matching_against_locationBase() {
        new PlaceholderBasedResourceLocationProvider("classpath:${one}", "two");
    }

    @Test(expected = IllegalArgumentException.class)
    public void creation_should_throw_exception_if_default_placeholderName_is_not_matching_against_locationBase() {
        new PlaceholderBasedResourceLocationProvider("classpath:${" + randomAlphanumeric(10) + "}");
    }

    @Test
    public void getLocation_should_replace_placeholder_with_given_value() {
        String locationBasePrefix = "classpath:";
        String id = randomAlphabetic(6);

        ResourceLocationProvider provider =
            new PlaceholderBasedResourceLocationProvider(locationBasePrefix + "${placeholderName}", "placeholderName");

        assertThat(provider.getLocation(id)).isNotNull().isEqualTo(locationBasePrefix + id);
    }

}
