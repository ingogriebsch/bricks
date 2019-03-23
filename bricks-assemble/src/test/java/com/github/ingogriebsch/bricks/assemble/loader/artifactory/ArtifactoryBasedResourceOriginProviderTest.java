package com.github.ingogriebsch.bricks.assemble.loader.artifactory;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.ingogriebsch.bricks.assemble.utils.artifactory.ArtifactoryConfiguration;

import org.junit.jupiter.api.Test;

public class ArtifactoryBasedResourceOriginProviderTest {

    @Test
    public void ctor_throws_exception_if_input_is_null() {
        assertThrows(NullPointerException.class, () -> new ArtifactoryBasedResourceOriginProvider(null));
    }

    @Test
    public void get_throws_exception_if_input_is_null() {
        ArtifactoryConfiguration configuration = new ArtifactoryConfiguration();
        configuration.setUrl("url");
        configuration.setAccessToken("token");

        assertThrows(NullPointerException.class, () -> new ArtifactoryBasedResourceOriginProvider(configuration).get(null));
    }

}
