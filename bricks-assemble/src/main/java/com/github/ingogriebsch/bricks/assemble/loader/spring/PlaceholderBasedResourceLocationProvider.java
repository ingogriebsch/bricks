/*-
 * #%L
 * Bricks Assemble
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
package com.github.ingogriebsch.bricks.assemble.loader.spring;

import static java.lang.String.format;

import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.PropertyPlaceholderHelper.PlaceholderResolver;

import lombok.NonNull;

public class PlaceholderBasedResourceLocationProvider implements ResourceLocationProvider {

    private static final String PLACEHOLDER_PREFIX = "${";
    private static final String PLACEHOLDER_SUFFIX = "}";
    private static final String DEFAULT_ID_PLACEHOLDER_NAME = "id";

    private static final PropertyPlaceholderHelper propertyPlaceholderHelper =
        new PropertyPlaceholderHelper(PLACEHOLDER_PREFIX, PLACEHOLDER_SUFFIX, null, false);

    private final String locationBase;
    private final String idPlaceholderName;

    public PlaceholderBasedResourceLocationProvider(String locationBase) {
        this(locationBase, DEFAULT_ID_PLACEHOLDER_NAME);
    }

    public PlaceholderBasedResourceLocationProvider(@NonNull String locationBase, @NonNull String idPlaceholderName) {
        validate(locationBase, idPlaceholderName);
        this.locationBase = locationBase;
        this.idPlaceholderName = idPlaceholderName;
    }

    @Override
    public String get(@NonNull String id) {
        return propertyPlaceholderHelper.replacePlaceholders(locationBase, replacements(id));
    }

    private PlaceholderResolver replacements(String id) {
        return new PlaceholderResolver() {

            @Override
            public String resolvePlaceholder(String placeholderName) {
                return idPlaceholderName.equals(placeholderName) ? id : null;
            }
        };
    }

    private static void validate(String locationBase, String idPlaceholderName) {
        if (!locationBase.contains(PLACEHOLDER_PREFIX + idPlaceholderName + PLACEHOLDER_SUFFIX)) {
            throw new IllegalArgumentException(
                format("The name of the placeholder for the id [%s] is not part of the location base [%s]!", idPlaceholderName,
                    locationBase));
        }
    }
}
