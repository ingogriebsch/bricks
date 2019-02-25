package com.github.ingogriebsch.bricks.assemble.assembler;

import com.github.ingogriebsch.bricks.model.Application;

public interface ApplicationAssembler {

    Application assemble(String id) throws Exception;

}
