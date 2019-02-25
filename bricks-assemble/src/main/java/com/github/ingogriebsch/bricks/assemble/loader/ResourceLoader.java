package com.github.ingogriebsch.bricks.assemble.loader;

import java.io.IOException;
import java.io.InputStream;

public interface ResourceLoader {

    InputStream load(String id) throws IOException;

}
