package com.github.ingogriebsch.bricks.assemble.reader.github;

import lombok.NonNull;

public class OneOnOneRepositoryIdProvider implements RepositoryIdProvider {

    @Override
    public String getId(@NonNull String id) {
        return id;
    }

}
