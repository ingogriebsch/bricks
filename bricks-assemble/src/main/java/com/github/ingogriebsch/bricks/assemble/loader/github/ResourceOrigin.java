package com.github.ingogriebsch.bricks.assemble.loader.github;

import lombok.Value;

@Value
public class ResourceOrigin {

    String repositoryId;
    String ref;
    String path;
}
