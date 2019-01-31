package com.github.ingogriebsch.bricks.assemble.reader.github;

import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class OneOnOneRepositoryIdProviderTest {

    @Test(expected = NullPointerException.class)
    public void getId_should_throw_exception_if_input_is_null() {
        new OneOnOneRepositoryIdProvider().getId(null);
    }

    @Test
    public void getId_should_return_given_input() {
        RepositoryIdProvider provider = new OneOnOneRepositoryIdProvider();

        newHashSet(randomAlphabetic(6)).stream().forEach(id -> {
            assertThat(provider.getId(id)).isNotNull().isEqualTo(id);
        });
    }
}
