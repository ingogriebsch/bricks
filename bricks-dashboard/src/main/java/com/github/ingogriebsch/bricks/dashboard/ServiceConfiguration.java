package com.github.ingogriebsch.bricks.dashboard;

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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
