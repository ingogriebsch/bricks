/*
 * Copyright © 2018 Ingo Griebsch (https://ingogriebsch.de/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.ingogriebsch.bricks.dashboard;

import static java.util.stream.Collectors.toSet;

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("bricks.dashboard")
public class ServiceProperties {

    @Getter
    @Setter
    private List<Application> applications;

    public Set<String> getApplicationIds() {
        return applications.stream().map(Application::getId).collect(toSet());
    }

    public Map<String, Set<String>> getComponentIds() {
        Map<String, Set<String>> result = newHashMap();
        for (Application application : applications) {
            result.put(application.getId(),
                newHashSet(application.getComponents().stream().map(c -> c.getId()).collect(toSet())));
        }
        return result;
    }

    @Data
    public static final class Application {

        private String id;
        private List<Component> components;
    }

    @Data
    public static final class Component {

        private String id;
    }

}
