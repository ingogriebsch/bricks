/*
 * Copyright © 2018 Ingo Griebsch (https://ingogriebsch.de/)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.ingogriebsch.bricks.assemble.reader.common;

import java.io.InputStream;

import com.github.ingogriebsch.bricks.assemble.converter.ComponentConverter;
import com.github.ingogriebsch.bricks.assemble.loader.ResourceLoader;
import com.github.ingogriebsch.bricks.assemble.reader.ComponentReader;
import com.github.ingogriebsch.bricks.assemble.utils.validate.Validator;
import com.github.ingogriebsch.bricks.model.Component;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimpleComponentReader implements ComponentReader {

    private final static Validator validator = new Validator();

    @NonNull
    private final ResourceLoader resourceLoader;
    @NonNull
    private final ComponentConverter componentConverter;
    private final boolean validate;

    public SimpleComponentReader(ResourceLoader resourceLoader, ComponentConverter componentConverter) {
        this(resourceLoader, componentConverter, true);
    }

    @Override
    public Component read(@NonNull String id) throws Exception {
        Component component = null;
        try (InputStream resource = resourceLoader.load(id)) {
            if (resource != null) {
                component = componentConverter.convert(resource);
            }
        }

        if (component != null && validate) {
            validator.validate(component);
        }
        return component;
    }
}
