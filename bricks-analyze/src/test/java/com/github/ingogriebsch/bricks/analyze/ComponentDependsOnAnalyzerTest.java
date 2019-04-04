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

import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.ingogriebsch.bricks.analyze.ComponentDependsOnAnalyzer.ComponentDependsOn;
import com.github.ingogriebsch.bricks.model.Communication;
import com.github.ingogriebsch.bricks.model.Component;
import com.github.ingogriebsch.bricks.model.Dependency;
import com.github.ingogriebsch.bricks.model.Interface;
import com.github.ingogriebsch.bricks.model.Messaging;

import org.junit.jupiter.api.Test;

public class ComponentDependsOnAnalyzerTest {

    @Test
    public void analyzer_should_throw_exception_if_originId_is_null() {
        assertThrows(NullPointerException.class,
            () -> ComponentDependsOnAnalyzer.builder().originId(null).components(newHashSet()).build());
    }

    @Test
    public void analyzer_should_throw_exception_if_components_is_null() {
        assertThrows(NullPointerException.class,
            () -> ComponentDependsOnAnalyzer.builder().originId("id").components(null).build());
    }

    @Test
    public void analyzer_should_return_null_if_component_matching_origin_id_is_not_available() {
        Component component = component("id2", null);

        ComponentDependsOnAnalyzer analyzer =
            ComponentDependsOnAnalyzer.builder().components(newHashSet(component)).originId("id1").build();
        assertThat(analyzer).isNotNull();

        assertThat(analyzer.analyze()).isNull();
    }

    @Test
    public void analyzer_should_return_matching_result_if_component_is_dependent_on_interface() {
        Component component2 = component("id2", communiction(intf("Tomcat", "https", "REST", "/api")));
        Component component1 =
            component("id1", communiction(depedency(component2.getId(), intf("Tomcat", "https", "REST", "/api"))));

        ComponentDependsOnAnalyzer analyzer = ComponentDependsOnAnalyzer.builder().components(newHashSet(component1, component2))
            .originId(component1.getId()).build();
        assertThat(analyzer).isNotNull();

        ComponentDependsOn cc = analyzer.analyze();
        assertThat(cc).isNotNull();
        assertThat(cc.origin()).isEqualTo(component1);
        assertThat(cc.interfaces()).containsExactly(component2);
        assertThat(cc.messaging()).isEmpty();
        assertThat(cc.targets()).containsExactly(component2);
    }

    @Test
    public void analyzer_should_return_matching_result_if_component_is_dependent_on_messaging() {
        Component component2 = component("id2", communiction(messaging("rabbitmq", "amqp")));
        Component component1 = component("id1", communiction(depedency(component2.getId(), messaging("rabbitmq", "amqp"))));

        ComponentDependsOnAnalyzer analyzer = ComponentDependsOnAnalyzer.builder().components(newHashSet(component1, component2))
            .originId(component1.getId()).build();
        assertThat(analyzer).isNotNull();

        ComponentDependsOn cc = analyzer.analyze();
        assertThat(cc).isNotNull();
        assertThat(cc.origin()).isEqualTo(component1);
        assertThat(cc.messaging()).containsExactly(component2);
        assertThat(cc.interfaces()).isEmpty();
        assertThat(cc.targets()).containsExactly(component2);
    }

    private static Component component(String id, Communication communication) {
        Component component = new Component();
        component.setId(id);
        component.setCommunication(communication);
        return component;
    }

    private static Communication communiction(Dependency... dependency) {
        Communication communication = new Communication();
        communication.setDependencies(newHashSet(dependency));
        return communication;
    }

    private static Communication communiction(Interface... intf) {
        Communication communication = new Communication();
        communication.setInterfaces(newHashSet(intf));
        return communication;
    }

    private static Communication communiction(Messaging messaging) {
        Communication communication = new Communication();
        communication.setMessaging(messaging);
        return communication;
    }

    private static Dependency depedency(String target, Messaging messaging) {
        Dependency dependency = dependency(target);
        dependency.setMessaging(messaging);
        return dependency;
    }

    private static Dependency depedency(String target, Interface intf) {
        Dependency dependency = dependency(target);
        dependency.setInterfaces(newHashSet(intf));
        return dependency;
    }

    private static Dependency dependency(String target) {
        Dependency dependency = new Dependency();
        dependency.setTarget(target);
        return dependency;
    }

    private static Interface intf(String provider, String protocol, String technology, String basePath) {
        Interface intf = new Interface();
        intf.setProvider(provider);
        intf.setProtocol(protocol);
        intf.setTechnology(technology);
        intf.setBasePath(basePath);
        return intf;
    }

    private static Messaging messaging(String carrier, String protocol) {
        Messaging messaging = new Messaging();
        messaging.setCarrier(carrier);
        messaging.setProtocol(protocol);
        return messaging;
    }
}
