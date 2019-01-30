package com.github.ingogriebsch.bricks.assemble.converter;

import java.io.IOException;
import java.io.InputStream;

import com.github.ingogriebsch.bricks.model.Component;

public interface ComponentConverter {

    Component convert(InputStream source) throws IOException;

}
