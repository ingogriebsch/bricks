package com.github.ingogriebsch.bricks.assemble.loader.github;

import static com.github.ingogriebsch.bricks.assemble.loader.github.SimpleResourceOriginProvider.DEFAULT_PATH;
import static com.github.ingogriebsch.bricks.assemble.loader.github.SimpleResourceOriginProvider.DEFAULT_REF;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class SimpleResourceOriginProviderTest {

    @Test
    public void builder_should_use_default_values_if_no_property_is_set_expicitely() {
        SimpleResourceOriginProvider provider = SimpleResourceOriginProvider.builder().build();

        assertThat(provider).isNotNull().hasFieldOrPropertyWithValue("path", DEFAULT_PATH).hasFieldOrPropertyWithValue("ref",
            DEFAULT_REF);
    }

    @Test
    public void builder_should_use_default_value_for_path_if_property_ref_is_set_explicitely() {
        String ref = randomAlphabetic(10);
        SimpleResourceOriginProvider provider = SimpleResourceOriginProvider.builder().ref(ref).build();

        assertThat(provider).isNotNull().hasFieldOrPropertyWithValue("path", DEFAULT_PATH).hasFieldOrPropertyWithValue("ref",
            ref);
    }

    @Test
    public void builder_should_use_default_value_for_ref_if_property_path_is_set_explicitely() {
        String path = randomAlphabetic(10);
        SimpleResourceOriginProvider provider = SimpleResourceOriginProvider.builder().path(path).build();

        assertThat(provider).isNotNull().hasFieldOrPropertyWithValue("path", path).hasFieldOrPropertyWithValue("ref",
            DEFAULT_REF);
    }

    @Test
    public void builder_should_use_values_if_set_explicitely() {
        String path = randomAlphabetic(10);
        String ref = randomAlphabetic(10);
        SimpleResourceOriginProvider provider = SimpleResourceOriginProvider.builder().path(path).ref(ref).build();

        assertThat(provider).isNotNull().hasFieldOrPropertyWithValue("path", path).hasFieldOrPropertyWithValue("ref", ref);
    }
}
