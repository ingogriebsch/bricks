/*
 * Copyright 2019 Ingo Griebsch
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package com.github.ingogriebsch.bricks.assemble.collector.common;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.unmodifiableSet;

import java.util.Set;

import com.github.ingogriebsch.bricks.assemble.collector.ComponentIdCollector;

import lombok.NonNull;

public class StaticComponentIdCollector implements ComponentIdCollector {

    @NonNull
    private final Set<String> componentIds;

    public StaticComponentIdCollector(Set<String> componentIds) {
        this.componentIds = unmodifiableSet(newHashSet(componentIds));
    }

    @Override
    public Set<String> collect(@NonNull String applicationId) {
        return componentIds;
    }

}
