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

import java.io.InputStream;

import com.github.ingogriebsch.bricks.assemble.collector.ApplicationCollector;
import com.github.ingogriebsch.bricks.assemble.collector.ApplicationIdCollector;
import com.github.ingogriebsch.bricks.assemble.collector.ComponentCollector;
import com.github.ingogriebsch.bricks.assemble.collector.ComponentIdCollector;
import com.github.ingogriebsch.bricks.assemble.collector.yaml.ApplicationIdOrigin;
import com.github.ingogriebsch.bricks.assemble.collector.yaml.ComponentIdOrigin;
import com.github.ingogriebsch.bricks.assemble.collector.yaml.YamlExtractingApplicationIdCollector;
import com.github.ingogriebsch.bricks.assemble.collector.yaml.YamlExtractingComponentIdCollector;
import com.github.ingogriebsch.bricks.assemble.collector.yaml.YamlResourceLoader;
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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Configuration
@RequiredArgsConstructor
public class ServiceConfiguration {

    private static final String SAMPLE_APPLICATIONS_LOCATION = "classpath:/example-applications.yml";
    private static final String[] YAML_PATH_APPLICATIONS = new String[] { "bricks", "dashboard", "applications" };
    private static final String[] YAML_PATH_COMPONENTS = new String[] { "bricks", "dashboard", "applications", "components" };
    private static final String YAML_KEY_ID = "id";

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
        return new YamlExtractingApplicationIdCollector(yamlResourceLoader(), applicationIdOrigin());
    }

    private ComponentIdCollector componentIdCollector() {
        return new YamlExtractingComponentIdCollector(yamlResourceLoader(), componentIdOrigin());
    }

    private ApplicationReader applicationReader() {
        return new SimpleApplicationReader(resourceLoader(YAML_PATH_APPLICATIONS), new Yaml2ApplicationConverter());
    }

    private ComponentReader componentReader() {
        return new SimpleComponentReader(resourceLoader(YAML_PATH_COMPONENTS), new Yaml2ComponentConverter());
    }

    private ResourceLoader resourceLoader(String... parents) {
        return new YamlExtractingResourceLoader(new SpringResourceBasedResourceLoader(resourceLoader,
            new StaticResourceLocationProvider(SAMPLE_APPLICATIONS_LOCATION)), YAML_KEY_ID, parents);
    }

    private ApplicationIdOrigin applicationIdOrigin() {
        return ApplicationIdOrigin.builder().parents(YAML_PATH_APPLICATIONS).build();
    }

    private ComponentIdOrigin componentIdOrigin() {
        return ComponentIdOrigin.builder().applicationIdOrigin(applicationIdOrigin()).build();
    }

    private YamlResourceLoader yamlResourceLoader() {
        return new YamlResourceLoader() {

            @Override
            @SneakyThrows
            public InputStream load() {
                return resourceLoader.getResource(SAMPLE_APPLICATIONS_LOCATION).getInputStream();
            }
        };
    }

}
