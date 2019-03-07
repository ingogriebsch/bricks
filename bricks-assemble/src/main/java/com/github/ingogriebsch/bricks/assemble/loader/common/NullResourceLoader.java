package com.github.ingogriebsch.bricks.assemble.loader.common;

import java.io.IOException;
import java.io.InputStream;

import com.github.ingogriebsch.bricks.assemble.loader.ResourceLoader;

public class NullResourceLoader implements ResourceLoader {

    @Override
    public InputStream load(String id) throws IOException {
        return null;
    }

}
