package com.github.ingogriebsch.bricks.assemble.loader.github;

import lombok.NonNull;

public class OneOnOneRepositoryIdProvider implements RepositoryIdProvider {

    @Override
    public String getId(@NonNull String id) {
        return id;
    }

}
