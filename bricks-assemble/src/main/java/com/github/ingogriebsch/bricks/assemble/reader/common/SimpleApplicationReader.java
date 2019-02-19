/*
 * Copyright 2019 Ingo Griebsch
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package com.github.ingogriebsch.bricks.assemble.reader.common;

import java.io.InputStream;

import com.github.ingogriebsch.bricks.assemble.converter.ApplicationConverter;
import com.github.ingogriebsch.bricks.assemble.loader.ResourceLoader;
import com.github.ingogriebsch.bricks.assemble.reader.ApplicationReader;
import com.github.ingogriebsch.bricks.assemble.utils.validate.Validator;
import com.github.ingogriebsch.bricks.model.Application;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimpleApplicationReader implements ApplicationReader {

    private static final Validator validator = new Validator();

    @NonNull
    private final ResourceLoader resourceLoader;
    @NonNull
    private final ApplicationConverter applicationConverter;
    private final boolean validate;

    public SimpleApplicationReader(ResourceLoader resourceLoader, ApplicationConverter applicationConverter) {
        this(resourceLoader, applicationConverter, true);
    }

    @Override
    public Application read(@NonNull String id) throws Exception {
        Application application = null;
        try (InputStream resource = resourceLoader.load(id)) {
            if (resource != null) {
                application = applicationConverter.convert(resource);
            }
        }

        if (application != null && validate) {
            validator.validate(application);
        }
        return application;
    }
}
