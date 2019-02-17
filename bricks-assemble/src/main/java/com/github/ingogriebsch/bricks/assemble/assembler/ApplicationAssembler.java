/*
 * Copyright 2019 Ingo Griebsch
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package com.github.ingogriebsch.bricks.assemble.assembler;

import java.io.InputStream;

import com.github.ingogriebsch.bricks.assemble.collector.ComponentCollector;
import com.github.ingogriebsch.bricks.assemble.converter.ApplicationConverter;
import com.github.ingogriebsch.bricks.assemble.loader.ApplicationResourceLoader;
import com.github.ingogriebsch.bricks.assemble.utils.validate.Validator;
import com.github.ingogriebsch.bricks.model.Application;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApplicationAssembler {

    private final Validator validator = new Validator();

    @NonNull
    private final ApplicationResourceLoader applicationResourceLoader;
    @NonNull
    private final ApplicationConverter applicationConverter;
    @NonNull
    private final ComponentCollector componentCollector;

    public Application assemble(@NonNull String id) throws Exception {
        Application application;
        try (InputStream source = applicationResourceLoader.read(id)) {
            application = applicationConverter.convert(source);
        }

        application.setComponents(componentCollector.collect(id));
        validator.validate(application);
        return application;
    }

}
