/*-
 * #%L
 * Bricks Analyze
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
package com.github.ingogriebsch.bricks.analyze;

import static java.util.stream.Collectors.toSet;

import static com.github.ingogriebsch.bricks.analyze.ComponentUtils.byId;
import static com.github.ingogriebsch.bricks.analyze.ComponentUtils.dependencies;
import static com.github.ingogriebsch.bricks.analyze.ComponentUtils.merge;
import static com.github.ingogriebsch.bricks.analyze.IterableUtils.isNullOrEmpty;
import static com.google.common.collect.Sets.newHashSet;

import java.util.Set;
import java.util.function.Predicate;

import com.github.ingogriebsch.bricks.model.Component;
import com.github.ingogriebsch.bricks.model.Dependency;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

@Builder
public class ComponentDependsOnAnalyzer {

    @NonNull
    private String originId;

    @NonNull
    private Set<Component> components;

    public ComponentDependsOn analyze() {
        Component origin = byId(originId, components);
        if (origin == null) {
            return null;
        }

        Set<Component> interfaces = collect(origin, components, isInterface());
        Set<Component> messaging = collect(origin, components, isMessaging());
        return new ComponentDependsOn(origin, interfaces, messaging);
    }

    private static Set<Component> collect(Component origin, Set<Component> components, Predicate<Dependency> filter) {
        return dependencies(origin, newHashSet()).stream().filter(filter).map(d -> byId(d.getTarget(), components))
            .filter(c -> c != null).collect(toSet());
    }

    private static Predicate<Dependency> isInterface() {
        return d -> !isNullOrEmpty(d.getInterfaces());
    }

    private static Predicate<Dependency> isMessaging() {
        return d -> d.getMessaging() != null;
    }

    @Accessors(fluent = true)
    @Value
    public static class ComponentDependsOn {

        @NonNull
        Component origin;
        @NonNull
        Set<Component> interfaces;
        @NonNull
        Set<Component> messaging;

        public Set<Component> targets() {
            return merge(interfaces, messaging);
        }
    }
}
