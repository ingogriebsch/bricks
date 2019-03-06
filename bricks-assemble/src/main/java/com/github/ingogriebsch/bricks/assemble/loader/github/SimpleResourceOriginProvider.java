package com.github.ingogriebsch.bricks.assemble.loader.github;

import lombok.Builder;
import lombok.NonNull;

@Builder
public class SimpleResourceOriginProvider implements ResourceOriginProvider {

    static final String DEFAULT_REF = "master";
    static final String DEFAULT_PATH = "bricks.json";

    @Builder.Default
    private String ref = DEFAULT_REF;
    @Builder.Default
    private String path = DEFAULT_PATH;

    @Override
    public ResourceOrigin get(@NonNull String componentId) {
        return new ResourceOrigin(componentId, ref, path);
    }

}
