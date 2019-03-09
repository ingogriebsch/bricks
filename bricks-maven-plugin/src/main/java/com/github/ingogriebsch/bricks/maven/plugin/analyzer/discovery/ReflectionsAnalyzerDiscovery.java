/*-
 * #%L
 * Bricks Maven Plugin
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
package com.github.ingogriebsch.bricks.maven.plugin.analyzer.discovery;

import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.stream.Collectors;

import com.github.ingogriebsch.bricks.maven.plugin.analyzer.AnalyzerContext;
import com.github.ingogriebsch.bricks.maven.plugin.analyzer.MavenAnalyzer;
import com.google.common.base.Predicates;

import org.reflections.Reflections;

import lombok.NonNull;
import lombok.SneakyThrows;

/**
 * AnalyzerDiscovery that uses "Reflections"-Framework to scan the classpath and
 * return instances of all concrete subclasses of MavenAnalyzer
 * 
 * @author doc
 *
 */
public class ReflectionsAnalyzerDiscovery implements AnalyzerDiscovery {

    @Override
    public Collection<MavenAnalyzer> findEnabledAnalyzers(@NonNull AnalyzerContext ctx) {

        ctx.log().info("Discovering Analyzers via 'Reflections'");

        // do not use ctx.reflections here, as that one is intended to include
        // project classes...

        return new Reflections().getSubTypesOf(MavenAnalyzer.class).stream().map(this::tryCreateInstance)
            .filter(Predicates.notNull()).collect(Collectors.toList());
    }

    @SneakyThrows
    private <T> T tryCreateInstance(Class<T> c) {
        if (c.isInterface() || Modifier.isAbstract(c.getModifiers()))
            return null;
        else
            return c.newInstance();
    }
}
