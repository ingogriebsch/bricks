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
package com.github.ingogriebsch.bricks.assemble.assembler.common;

import java.util.Set;

import com.github.ingogriebsch.bricks.assemble.assembler.ApplicationAssembler;
import com.github.ingogriebsch.bricks.assemble.collector.ComponentCollector;
import com.github.ingogriebsch.bricks.assemble.reader.ApplicationReader;
import com.github.ingogriebsch.bricks.assemble.utils.validate.Validator;
import com.github.ingogriebsch.bricks.model.Application;
import com.github.ingogriebsch.bricks.model.Component;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ComponentCollectingApplicationAssembler implements ApplicationAssembler {

    private static final Validator validator = new Validator();

    @NonNull
    private final ApplicationReader applicationReader;
    @NonNull
    private final ComponentCollector componentCollector;
    private final boolean validate;

    public ComponentCollectingApplicationAssembler(ApplicationReader applicationReader, ComponentCollector componentCollector) {
        this(applicationReader, componentCollector, true);
    }

    @Override
    public Application assemble(@NonNull String id) throws Exception {
        Application application = applicationReader.read(id);
        if (application == null) {
            return null;
        }

        Set<Component> components = componentCollector.collect(id);
        components = merge(components, application.getComponents());
        application.setComponents(components);

        if (validate) {
            validator.validate(application);
        }
        return application;
    }

    private static Set<Component> merge(Set<Component> collected, Set<Component> fromApplication) {
        return collected;
    }

}
