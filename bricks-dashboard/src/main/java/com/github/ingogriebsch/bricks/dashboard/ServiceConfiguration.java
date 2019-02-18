/*
 * Copyright 2019 Ingo Griebsch
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package com.github.ingogriebsch.bricks.dashboard;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.ingogriebsch.bricks.assemble.assembler.ApplicationAssembler;
import com.github.ingogriebsch.bricks.assemble.assembler.common.ComponentCollectingApplicationAssembler;
import com.github.ingogriebsch.bricks.assemble.collector.ApplicationCollector;
import com.github.ingogriebsch.bricks.assemble.collector.ApplicationIdCollector;
import com.github.ingogriebsch.bricks.assemble.collector.ComponentCollector;
import com.github.ingogriebsch.bricks.assemble.collector.ComponentIdCollector;
import com.github.ingogriebsch.bricks.assemble.collector.common.StaticApplicationIdCollector;
import com.github.ingogriebsch.bricks.assemble.collector.common.StaticComponentIdCollector;
import com.github.ingogriebsch.bricks.assemble.converter.json.Json2ApplicationConverter;
import com.github.ingogriebsch.bricks.assemble.converter.json.Json2ComponentConverter;
import com.github.ingogriebsch.bricks.assemble.loader.ResourceLoader;
import com.github.ingogriebsch.bricks.assemble.loader.spring.PlaceholderBasedResourceLocationProvider;
import com.github.ingogriebsch.bricks.assemble.loader.spring.ResourceLocationProvider;
import com.github.ingogriebsch.bricks.assemble.loader.spring.SpringResourceBasedResourceLoader;
import com.github.ingogriebsch.bricks.assemble.reader.ApplicationReader;
import com.github.ingogriebsch.bricks.assemble.reader.ComponentReader;
import com.github.ingogriebsch.bricks.assemble.reader.common.SimpleApplicationReader;
import com.github.ingogriebsch.bricks.assemble.reader.common.SimpleComponentReader;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ServiceConfiguration {

    @NonNull
    private final ServiceProperties serviceProperties;

    @NonNull
    private final org.springframework.core.io.ResourceLoader resourceLoader;

    @Bean
    public ApplicationCollector applicationCollector() {
        return new ApplicationCollector(applicationIdCollector(), applicationAssembler());
    }

    private ApplicationAssembler applicationAssembler() {
        return new ComponentCollectingApplicationAssembler(applicationReader(), componentCollector());
    }

    private ComponentCollector componentCollector() {
        return new ComponentCollector(componentIdCollector(), componentReader());
    }

    private ComponentReader componentReader() {
        return new SimpleComponentReader(resourceLoader(), new Json2ComponentConverter());
    }

    private ComponentIdCollector componentIdCollector() {
        return new StaticComponentIdCollector(serviceProperties.getComponentIds());
    }

    private ApplicationReader applicationReader() {
        return new SimpleApplicationReader(resourceLoader(), new Json2ApplicationConverter());
    }

    private ResourceLoader resourceLoader() {
        return new SpringResourceBasedResourceLoader(resourceLoader, resourceLocationProvider());
    }

    private ResourceLocationProvider resourceLocationProvider() {
        return new PlaceholderBasedResourceLocationProvider("classpath:/applications/${id}.json");
    }

    private ApplicationIdCollector applicationIdCollector() {
        return new StaticApplicationIdCollector(serviceProperties.getApplicationIds());
    }

}
