package com.github.ingogriebsch.bricks.dashboard.web;

import java.util.List;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Builder
@Value
public class Breadcrumb {

    @Singular
    private List<Entry> entries;

    @Builder
    @Value
    public static class Entry {

        private String name;
        private String href;
    }
}
