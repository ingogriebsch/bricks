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
package com.github.ingogriebsch.bricks.dashboard.web;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.apache.commons.lang3.StringUtils.join;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;
import java.util.List;

import com.github.ingogriebsch.bricks.dashboard.web.Breadcrumb.Entry;
import com.github.ingogriebsch.bricks.model.Application;
import com.github.ingogriebsch.bricks.model.Component;

import org.junit.jupiter.api.Test;

public class BreadcrumbFactoryTest {

    @Test
    public void breadrumb_should_contain_matching_entities_if_applications_is_accessed() {
        String[] parts = new String[] { "applications" };
        String path = path(parts);

        Breadcrumb breadcrumb = BreadcrumbFactory.create(path);
        assertThat(breadcrumb).isNotNull();

        List<Entry> entries = breadcrumb.getEntries();
        assertThat(entries).isNotNull().hasSize(1);

        Entry entry = entries.iterator().next();
        assertThat(entry).isNotNull().hasFieldOrPropertyWithValue("href", path);
    }

    @Test
    public void breadrumb_should_contain_matching_entities_if_application_is_accessed() {
        String[] parts = new String[] { "applications", "{applicationId}" };
        String path = path(parts);

        Application application = new Application();
        application.setId(randomNumeric(6));
        application.setName(randomAlphabetic(12));

        Breadcrumb breadcrumb = BreadcrumbFactory.create(path, application);
        assertThat(breadcrumb).isNotNull();

        List<Entry> entries = breadcrumb.getEntries();
        assertThat(entries).isNotNull().hasSize(2);

        Iterator<Entry> iterator = entries.iterator();
        Entry entry = iterator.next();
        assertThat(entry).isNotNull().hasFieldOrPropertyWithValue("href", path(parts[0]));

        entry = iterator.next();
        assertThat(entry).isNotNull().hasFieldOrPropertyWithValue("href", path(parts[0], application.getId()));
    }

    @Test
    public void breadrumb_should_contain_matching_entities_if_application_overview_is_accessed() {
        String[] parts = new String[] { "applications", "{applicationId}", "overview" };
        String path = path(parts);

        Application application = new Application();
        application.setId(randomNumeric(6));
        application.setName(randomAlphabetic(12));

        Breadcrumb breadcrumb = BreadcrumbFactory.create(path, application);
        assertThat(breadcrumb).isNotNull();

        List<Entry> entries = breadcrumb.getEntries();
        assertThat(entries).isNotNull().hasSize(3);

        Iterator<Entry> iterator = entries.iterator();
        Entry entry = iterator.next();
        assertThat(entry).isNotNull().hasFieldOrPropertyWithValue("href", path(parts[0]));

        entry = iterator.next();
        assertThat(entry).isNotNull().hasFieldOrPropertyWithValue("href", path(parts[0], application.getId()));

        entry = iterator.next();
        assertThat(entry).isNotNull().hasFieldOrPropertyWithValue("href", path(parts[0], application.getId(), parts[2]));
    }

    @Test
    public void breadrumb_should_contain_matching_entities_if_component_is_accessed() {
        String[] parts = new String[] { "applications", "{applicationId}", "components", "{componentId}" };
        String path = "/" + join(parts, "/");

        Application application = new Application();
        application.setId(randomNumeric(6));
        application.setName(randomAlphabetic(12));

        Component component = new Component();
        component.setId(randomNumeric(6));
        component.setName(randomAlphabetic(12));

        Breadcrumb breadcrumb = BreadcrumbFactory.create(path, application, component);
        assertThat(breadcrumb).isNotNull();

        List<Entry> entries = breadcrumb.getEntries();
        assertThat(entries).isNotNull().hasSize(4);

        Iterator<Entry> iterator = entries.iterator();
        Entry entry = iterator.next();
        assertThat(entry).isNotNull().hasFieldOrPropertyWithValue("href", path(parts[0]));

        entry = iterator.next();
        assertThat(entry).isNotNull().hasFieldOrPropertyWithValue("href", path(parts[0], application.getId()));

        entry = iterator.next();
        assertThat(entry).isNotNull().hasFieldOrPropertyWithValue("href", path(parts[0], application.getId(), parts[2]));

        entry = iterator.next();
        assertThat(entry).isNotNull().hasFieldOrPropertyWithValue("href",
            path(parts[0], application.getId(), parts[2], component.getId()));
    }

    @Test
    public void breadrumb_should_contain_matching_entities_if_component_overview_is_accessed() {
        String[] parts = new String[] { "applications", "{applicationId}", "components", "{componentId}", "overview" };
        String path = "/" + join(parts, "/");

        Application application = new Application();
        application.setId(randomNumeric(6));
        application.setName(randomAlphabetic(12));

        Component component = new Component();
        component.setId(randomNumeric(6));
        component.setName(randomAlphabetic(12));

        Breadcrumb breadcrumb = BreadcrumbFactory.create(path, application, component);
        assertThat(breadcrumb).isNotNull();

        List<Entry> entries = breadcrumb.getEntries();
        assertThat(entries).isNotNull().hasSize(5);

        Iterator<Entry> iterator = entries.iterator();
        Entry entry = iterator.next();
        assertThat(entry).isNotNull().hasFieldOrPropertyWithValue("href", path(parts[0]));

        entry = iterator.next();
        assertThat(entry).isNotNull().hasFieldOrPropertyWithValue("href", path(parts[0], application.getId()));

        entry = iterator.next();
        assertThat(entry).isNotNull().hasFieldOrPropertyWithValue("href", path(parts[0], application.getId(), parts[2]));

        entry = iterator.next();
        assertThat(entry).isNotNull().hasFieldOrPropertyWithValue("href",
            path(parts[0], application.getId(), parts[2], component.getId()));

        entry = iterator.next();
        assertThat(entry).isNotNull().hasFieldOrPropertyWithValue("href",
            path(parts[0], application.getId(), parts[2], component.getId(), parts[4]));
    }

    private static String path(String... parts) {
        return "/" + join(parts, "/");
    }

}
