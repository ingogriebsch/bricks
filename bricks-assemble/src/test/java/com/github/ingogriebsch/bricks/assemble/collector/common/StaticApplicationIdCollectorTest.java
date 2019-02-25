package com.github.ingogriebsch.bricks.assemble.collector.common;

import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import com.github.ingogriebsch.bricks.assemble.collector.ApplicationIdCollector;

import org.junit.Test;

public class StaticApplicationIdCollectorTest {

    @Test(expected = NullPointerException.class)
    public void creation_should_throw_exception_if_input_is_null() {
        new StaticApplicationIdCollector(null);
    }

    @Test
    public void collect_should_return_given_input() {
        Set<String> applicationIds = newHashSet(randomAlphabetic(6), randomAlphabetic(6), randomAlphabetic(6));
        ApplicationIdCollector collector = new StaticApplicationIdCollector(newHashSet(applicationIds));
        assertThat(collector.collect()).isNotNull().isEqualTo(applicationIds);
    }

}
