package com.github.ingogriebsch.bricks.assemble.reader.resource;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ResourceLoader;

import com.github.ingogriebsch.bricks.assemble.reader.ApplicationReader;

import lombok.NonNull;

public class ResourceApplicationReader extends AbstractResourceBasedReader implements ApplicationReader {

    public ResourceApplicationReader(ResourceLoader resourceLoader, ResourceLocationProvider resourceLocationProvider) {
        super(resourceLoader, resourceLocationProvider);
    }

    @Override
    public InputStream read(@NonNull String id) throws IOException {
        return readResource(id);
    }

}
