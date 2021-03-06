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
package com.github.ingogriebsch.bricks.assemble.collector;

import static java.lang.String.format;

import java.util.HashSet;
import java.util.Set;

import com.github.ingogriebsch.bricks.assemble.reader.ComponentReader;
import com.github.ingogriebsch.bricks.assemble.reader.ComponentReaderFactory;
import com.github.ingogriebsch.bricks.model.Component;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ComponentCollector {

    @NonNull
    private final ComponentIdCollector componentIdCollector;
    @NonNull
    private final ComponentReaderFactory componentReaderFactory;

    public Set<Component> collect(@NonNull String applicationId) throws Exception {
        Set<String> componentIds = componentIdCollector.collect(applicationId);

        Set<Component> result = new HashSet<>(componentIds.size());
        for (String id : componentIds) {
            ComponentReader reader = componentReaderFactory.create(id);
            if (reader == null) {
                throw new IllegalStateException(
                    format("No reader found for component '%s' of application '%s'!", id, applicationId));
            }

            Component component = reader.read(id);
            if (component != null) {
                result.add(component);
            }
        }
        return result;
    }

}
