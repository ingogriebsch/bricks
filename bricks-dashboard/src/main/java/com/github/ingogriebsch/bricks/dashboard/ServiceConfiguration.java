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
package com.github.ingogriebsch.bricks.dashboard;

import java.util.Map;
import java.util.Set;

import com.github.ingogriebsch.bricks.assemble.collector.ApplicationCollector;
import com.github.ingogriebsch.bricks.assemble.collector.ApplicationIdCollector;
import com.github.ingogriebsch.bricks.assemble.collector.ComponentCollector;
import com.github.ingogriebsch.bricks.assemble.collector.ComponentIdCollector;
import com.github.ingogriebsch.bricks.assemble.collector.common.StaticApplicationIdCollector;
import com.github.ingogriebsch.bricks.assemble.collector.common.StaticComponentIdCollector;
import com.github.ingogriebsch.bricks.assemble.converter.yaml.Yaml2ApplicationConverter;
import com.github.ingogriebsch.bricks.assemble.converter.yaml.Yaml2ComponentConverter;
import com.github.ingogriebsch.bricks.assemble.loader.ResourceLoader;
import com.github.ingogriebsch.bricks.assemble.loader.spring.SpringResourceBasedResourceLoader;
import com.github.ingogriebsch.bricks.assemble.loader.spring.StaticResourceLocationProvider;
import com.github.ingogriebsch.bricks.assemble.loader.yaml.YamlExtractingResourceLoader;
import com.github.ingogriebsch.bricks.assemble.reader.ApplicationReader;
import com.github.ingogriebsch.bricks.assemble.reader.ComponentReader;
import com.github.ingogriebsch.bricks.assemble.reader.common.SimpleApplicationReader;
import com.github.ingogriebsch.bricks.assemble.reader.common.SimpleComponentReader;
import com.github.ingogriebsch.bricks.assemble.reader.common.StaticApplicationReaderFactory;
import com.github.ingogriebsch.bricks.assemble.reader.common.StaticComponentReaderFactory;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ServiceConfiguration {

    @NonNull
    private final org.springframework.core.io.ResourceLoader resourceLoader;

    @Bean
    public ApplicationCollector applicationCollector() {
        return new ApplicationCollector(applicationIdCollector(), new StaticApplicationReaderFactory(applicationReader()));
    }

    @Bean
    public ComponentCollector componentCollector() {
        return new ComponentCollector(componentIdCollector(), new StaticComponentReaderFactory(componentReader()));
    }

    private ApplicationIdCollector applicationIdCollector() {
        return new StaticApplicationIdCollector(Sets.newHashSet("sample-app"));
    }

    private ComponentIdCollector componentIdCollector() {
        Map<String, Set<String>> componentIds = Maps.newHashMap();
        componentIds.put("sample-app", Sets.newHashSet("component1", "component2"));
        return new StaticComponentIdCollector(componentIds);
    }

    private ApplicationReader applicationReader() {
        return new SimpleApplicationReader(resourceLoader("bricks", "dashboard", "applications"),
            new Yaml2ApplicationConverter());
    }

    private ComponentReader componentReader() {
        return new SimpleComponentReader(resourceLoader("bricks", "dashboard", "applications", "components"),
            new Yaml2ComponentConverter());
    }

    private ResourceLoader resourceLoader(String... parents) {
        return new YamlExtractingResourceLoader(new SpringResourceBasedResourceLoader(resourceLoader,
            new StaticResourceLocationProvider("classpath:/example-applications.yml")), "id", parents);
    }
}
