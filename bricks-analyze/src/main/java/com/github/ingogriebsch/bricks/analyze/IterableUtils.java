package com.github.ingogriebsch.bricks.analyze;

import static com.google.common.collect.Iterables.isEmpty;

import lombok.experimental.UtilityClass;

@UtilityClass
public class IterableUtils {

    public boolean isNullOrEmpty(Iterable<?> entries) {
        return entries == null || isEmpty(entries);
    }

}
