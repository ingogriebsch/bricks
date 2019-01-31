package com.github.ingogriebsch.bricks.assemble.collector.common;

import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.Test;

import com.github.ingogriebsch.bricks.assemble.collector.ComponentIdCollector;

public class StaticComponentIdCollectorTest {

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_input_is_null() {
        new StaticComponentIdCollector(null);
    }

    @Test(expected = NullPointerException.class)
    public void collect_should_throw_exception_if_input_is_null() {
        new StaticComponentIdCollector(newHashSet(randomAlphabetic(6))).collect(null);
    }

    @Test
    public void collect_should_return_given_input() {
        String applicationId = randomAlphabetic(6);
        Set<String> componentIds = newHashSet(randomAlphabetic(6), randomAlphabetic(6), randomAlphabetic(6));

        ComponentIdCollector collector = new StaticComponentIdCollector(componentIds);
        assertThat(collector.collect(applicationId)).isNotNull().isEqualTo(componentIds);
    }

    @Test
    public void collect_should_return_given_input_even_if_source_is_changed_afterwards() {
        String applicationId = randomAlphabetic(6);
        Set<String> componentIds = newHashSet(randomAlphabetic(6), randomAlphabetic(6), randomAlphabetic(6));

        ComponentIdCollector collector = new StaticComponentIdCollector(componentIds);

        Set<String> originalComponentIds = newHashSet(componentIds);
        componentIds.add(randomAlphabetic(6));

        assertThat(collector.collect(applicationId)).isNotNull().isEqualTo(originalComponentIds);
    }

    @Test
    public void collect_should_return_given_input_regardless_of_the_given_applicationId() {
        Set<String> componentIds = newHashSet(randomAlphabetic(6), randomAlphabetic(6), randomAlphabetic(6));

        ComponentIdCollector collector = new StaticComponentIdCollector(componentIds);
        newHashSet(randomAlphabetic(6), randomAlphabetic(6), randomAlphabetic(6)).stream().forEach(id -> {
            assertThat(collector.collect(id)).isNotNull().isEqualTo(componentIds);
        });

    }
}
