package com.github.ingogriebsch.bricks.assemble.loader.common;

import java.io.IOException;
import java.io.InputStream;

import com.github.ingogriebsch.bricks.assemble.loader.ResourceLoader;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InputStreamResourceLoader implements ResourceLoader {

    @NonNull
    private final InputStream inputStream;

    @Override
    public InputStream load(String id) throws IOException {
        return inputStream;
    }

}
