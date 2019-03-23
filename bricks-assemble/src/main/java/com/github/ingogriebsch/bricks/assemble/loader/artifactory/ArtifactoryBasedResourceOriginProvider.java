package com.github.ingogriebsch.bricks.assemble.loader.artifactory;

import com.github.ingogriebsch.bricks.assemble.utils.artifactory.ArtifactoryConfiguration;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArtifactoryBasedResourceOriginProvider implements ResourceOriginProvider {

    @NonNull
    private final ArtifactoryConfiguration confiuration;

    @Override
    public ResourceOrigin get(@NonNull String componentId) {
        // TODO Auto-generated method stub
        return null;
    }

}
