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

import static java.util.Arrays.asList;
import static java.util.Arrays.copyOf;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang3.StringUtils.capitalize;

import java.util.List;
import java.util.Properties;

import com.github.ingogriebsch.bricks.dashboard.web.Breadcrumb.BreadcrumbBuilder;
import com.github.ingogriebsch.bricks.dashboard.web.Breadcrumb.Entry;
import com.github.ingogriebsch.bricks.model.Application;
import com.github.ingogriebsch.bricks.model.Component;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.PropertyPlaceholderHelper;

import lombok.NonNull;

public class BreadcrumbFactory {

    private static final PropertyPlaceholderHelper propertyPlaceholderHelper =
        new PropertyPlaceholderHelper("{", "}", null, false);

    public static Breadcrumb create(@NonNull String path) {
        return build(path, null, null);
    }

    public static Breadcrumb create(@NonNull String path, @NonNull Application application) {
        return build(path, application, null);
    }

    public static Breadcrumb create(@NonNull String path, @NonNull Application application, @NonNull Component component) {
        return build(path, application, component);
    }

    private static Breadcrumb build(String path, Application application, Component component) {
        BreadcrumbBuilder builder = Breadcrumb.builder();
        List<List<String>> paths = split(path);
        for (List<String> part : paths) {
            builder.entry(entry(name(part, application, component), href(part, application, component)));
        }
        return builder.build();
    }

    private static List<List<String>> split(String path) {
        List<List<String>> result = newArrayList();
        String[] parts = StringUtils.split(path, "/");
        for (int i = 1; i <= parts.length; i++) {
            result.add(asList(copyOf(parts, i)));
        }
        return result;
    }

    private static String join(List<String> path) {
        return "/" + StringUtils.join(path, "/");
    }

    private static String name(List<String> path, Application application, Component component) {
        String last = path.get(path.size() - 1);
        if (last.equals("{applicationId}")) {
            return application.getName();
        }
        if (last.equals("{componentId}")) {
            return component.getName();
        }
        return capitalize(last);
    }

    private static String href(List<String> path, Application application, Component component) {
        Properties properties = new Properties();
        if (application != null) {
            properties.put("applicationId", application.getId());
        }
        if (component != null) {
            properties.put("componentId", component.getId());
        }
        return propertyPlaceholderHelper.replacePlaceholders(join(path), properties);
    }

    private static Entry entry(String name, String href) {
        return Entry.builder().name(name).href(href).build();
    }

}
