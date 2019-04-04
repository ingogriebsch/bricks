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

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Stream.concat;

import static com.google.common.collect.Sets.newHashSet;
import static com.google.common.collect.Streams.stream;
import static lombok.AccessLevel.PRIVATE;

import java.util.Set;

import com.github.ingogriebsch.bricks.model.Communication;
import com.github.ingogriebsch.bricks.model.Component;
import com.github.ingogriebsch.bricks.model.Dependency;

import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = PRIVATE)
public final class ComponentUtils {

    public static Component byId(@NonNull String id, @NonNull Iterable<Component> components) {
        return stream(components).filter(c -> id.equals(c.getId())).findAny().orElse(null);
    }

    public static <T> Set<Component> merge(@NonNull Iterable<Component> one, @NonNull Iterable<Component> two) {
        return newHashSet(concat(stream(one), stream(two)).collect(toMap(Component::getId, identity(), (a, b) -> a)).values());
    }

    public static Set<Dependency> dependencies(@NonNull Component component, Set<Dependency> defaultValue) {
        Communication communication = component.getCommunication();
        Set<Dependency> dependencies = communication != null ? communication.getDependencies() : null;
        return dependencies != null ? dependencies : defaultValue;
    }
}
