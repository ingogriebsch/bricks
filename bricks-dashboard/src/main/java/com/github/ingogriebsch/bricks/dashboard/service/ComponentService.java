/*-
 * #%L
 * Bricks Dashboard
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
package com.github.ingogriebsch.bricks.dashboard.service;

import static com.google.common.collect.MoreCollectors.toOptional;

import java.util.Optional;
import java.util.Set;

import com.github.ingogriebsch.bricks.assemble.collector.ComponentCollector;
import com.github.ingogriebsch.bricks.model.Component;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Component
@RequiredArgsConstructor
public class ComponentService {

    @NonNull
    private final ComponentCollector componentCollector;

    public Set<Component> findAll(@NonNull String applicationId) throws Exception {
        return componentCollector.collect(applicationId);
    }

    public Optional<Component> findOne(@NonNull String applicationId, @NonNull String componentId) throws Exception {
        return findAll(applicationId).stream().filter(a -> componentId.equals(a.getId())).collect(toOptional());
    }
}
