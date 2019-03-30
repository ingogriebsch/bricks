package com.github.ingogriebsch.bricks.analyze;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Stream.concat;

import static com.google.common.collect.Sets.newHashSet;
import static com.google.common.collect.Streams.stream;

import java.util.Set;

import com.github.ingogriebsch.bricks.model.Communication;
import com.github.ingogriebsch.bricks.model.Component;
import com.github.ingogriebsch.bricks.model.Dependency;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ComponentUtils {

    public Component byId(@NonNull String id, @NonNull Iterable<Component> components) {
        return stream(components).filter(c -> id.equals(c.getId())).findAny().orElse(null);
    }

    public <T> Set<Component> merge(@NonNull Iterable<Component> one, @NonNull Iterable<Component> two) {
        return newHashSet(concat(stream(one), stream(two)).collect(toMap(Component::getId, identity(), (a, b) -> a)).values());
    }

    public Set<Dependency> dependencies(@NonNull Component component, Set<Dependency> defaultValue) {
        Communication communication = component.getCommunication();
        Set<Dependency> dependencies = communication != null ? communication.getDependencies() : null;
        return dependencies != null ? dependencies : defaultValue;
    }
}
