package com.github.ingogriebsch.bricks.assemble.loader.spring;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SpringResourceBasedResourceLoader implements com.github.ingogriebsch.bricks.assemble.loader.ResourceLoader {

    @NonNull
    private final ResourceLoader resourceLoader;
    @NonNull
    private final ResourceLocationProvider resourceLocationProvider;

    @Override
    public InputStream load(@NonNull String id) throws IOException {
        Resource resource = resourceLoader.getResource(resourceLocationProvider.getLocation(id));
        if (!resource.exists() || !resource.isReadable()) {
            return null;
        }
        return resource.getInputStream();
    }

}
