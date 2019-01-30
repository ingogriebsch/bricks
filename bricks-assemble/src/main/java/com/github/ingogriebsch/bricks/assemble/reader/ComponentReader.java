package com.github.ingogriebsch.bricks.assemble.reader;

import java.io.IOException;
import java.io.InputStream;

public interface ComponentReader {

    InputStream read(String id) throws IOException;

}
