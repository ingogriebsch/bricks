package com.github.ingogriebsch.bricks.analyze;

import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class IterableUtilsTest {

    @Test
    public void isNullOrEmpty_should_return_true_if_input_is_null() {
        assertThat(IterableUtils.isNullOrEmpty(null)).isTrue();
    }

    @Test
    public void isNullOrEmpty_should_return_true_if_input_is_empty() {
        assertThat(IterableUtils.isNullOrEmpty(newHashSet())).isTrue();
    }

    @Test
    public void isNullOrEmpty_should_return_false_if_input_is_not_empty() {
        assertThat(IterableUtils.isNullOrEmpty(newHashSet("Some", "value"))).isFalse();
    }
}
