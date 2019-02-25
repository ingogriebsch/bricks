package com.github.ingogriebsch.bricks.assemble.reader.common;

import com.github.ingogriebsch.bricks.assemble.reader.ComponentReader;
import com.github.ingogriebsch.bricks.assemble.reader.ComponentReaderFactory;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StaticComponentReaderFactory implements ComponentReaderFactory {

    @NonNull
    private final ComponentReader componentReader;

    @Override
    public ComponentReader create(@NonNull String id) {
        return componentReader;
    }

}
