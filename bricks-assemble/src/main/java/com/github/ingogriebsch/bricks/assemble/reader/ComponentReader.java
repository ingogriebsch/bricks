package com.github.ingogriebsch.bricks.assemble.reader;

import com.github.ingogriebsch.bricks.model.Component;

public interface ComponentReader {

    public Component read(String id) throws Exception;

}
