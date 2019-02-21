package com.github.ingogriebsch.bricks.assemble.reader.common;

import com.github.ingogriebsch.bricks.assemble.reader.ApplicationReader;
import com.github.ingogriebsch.bricks.assemble.reader.ApplicationReaderFactory;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StaticApplicationReaderFactory implements ApplicationReaderFactory {

    @NonNull
    private final ApplicationReader applicationReader;

    @Override
    public ApplicationReader create(@NonNull String id) {
        return applicationReader;
    }

}
