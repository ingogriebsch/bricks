package com.github.ingogriebsch.bricks.assemble.loader.common;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StaticZipEntryLocationProvider implements ZipEntryLocationProvider {

    @NonNull
    private final String location;

    @Override
    public String get(String componentId) {
        return location;
    }

}
