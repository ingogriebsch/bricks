package com.github.ingogriebsch.bricks.assemble.converter;

import java.io.IOException;
import java.io.InputStream;

import com.github.ingogriebsch.bricks.model.Application;

public interface ApplicationConverter {

    Application convert(InputStream source) throws IOException;

}
