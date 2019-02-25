package com.github.ingogriebsch.bricks.assemble.reader;

import com.github.ingogriebsch.bricks.model.Application;

public interface ApplicationReader {

    public Application read(String id) throws Exception;

}
