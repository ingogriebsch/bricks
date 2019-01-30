package com.github.ingogriebsch.bricks.assemble.reader.resource;

import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.PropertyPlaceholderHelper.PlaceholderResolver;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlaceholderBasedResourceLocationProvider implements ResourceLocationProvider {

    private static final PropertyPlaceholderHelper propertyPlaceholderHelper =
        new PropertyPlaceholderHelper("${", "}", null, false);

    @NonNull
    private final String locationBase;
    @NonNull
    private final String idPlaceholderName;

    public PlaceholderBasedResourceLocationProvider(String locationBase) {
        this(locationBase, "id");
    }

    @Override
    public String getLocation(@NonNull String id) {
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
}
