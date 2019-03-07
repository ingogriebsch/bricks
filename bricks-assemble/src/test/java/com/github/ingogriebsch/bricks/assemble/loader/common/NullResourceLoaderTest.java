package com.github.ingogriebsch.bricks.assemble.loader.common;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class NullResourceLoaderTest {

    @Test
    public void load_should_return_null_regardless_which_id_is_given() throws Exception {
        assertThat(new NullResourceLoader().load(randomAlphabetic(12))).isNull();
    }
}
