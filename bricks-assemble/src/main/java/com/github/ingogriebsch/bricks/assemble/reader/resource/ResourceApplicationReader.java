/*
 * Copyright 2019 Ingo Griebsch
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
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
