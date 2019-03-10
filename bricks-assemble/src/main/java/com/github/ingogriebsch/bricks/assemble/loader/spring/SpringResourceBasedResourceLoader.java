/*-
 * #%L
 * Bricks Assemble
 * %%
 * Copyright (C) 2018 - 2019 Ingo Griebsch
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
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
        Resource resource = resourceLoader.getResource(resourceLocationProvider.get(id));
        if (!resource.exists() || !resource.isReadable()) {
            return null;
        }
        return resource.getInputStream();
    }

}
