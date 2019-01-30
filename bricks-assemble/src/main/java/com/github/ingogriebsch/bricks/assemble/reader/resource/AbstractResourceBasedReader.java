package com.github.ingogriebsch.bricks.assemble.reader.resource;

import static lombok.AccessLevel.PROTECTED;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = PROTECTED)
abstract class AbstractResourceBasedReader {

    @NonNull
    private final ResourceLoader resourceLoader;
    @NonNull
    private final ResourceLocationProvider resourceLocationProvider;

    protected InputStream readResource(@NonNull String id) throws IOException {
        Resource resource = resourceLoader.getResource(resourceLocationProvider.getLocation(id));
        if (!resource.exists() || !resource.isReadable()) {
            return null;
        }
        return resource.getInputStream();
    }

}
