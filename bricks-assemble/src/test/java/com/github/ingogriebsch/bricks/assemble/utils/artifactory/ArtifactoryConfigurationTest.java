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
package com.github.ingogriebsch.bricks.assemble.utils.artifactory;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ValidationException;

import com.github.ingogriebsch.bricks.assemble.utils.artifactory.ArtifactoryConfiguration.Credentials;
import com.github.ingogriebsch.bricks.assemble.utils.validate.Validator;

import org.junit.jupiter.api.Test;

public class ArtifactoryConfigurationTest {

    @Test
    public void validate_should_fail_if_neither_access_token_nor_credentials_is_set() {
        ArtifactoryConfiguration configuration = new ArtifactoryConfiguration();
        configuration.setUrl("url");

        assertThrows(ValidationException.class, () -> new Validator().validate(configuration));
    }

    @Test
    public void validate_should_fail_if_access_token_as_well_as_credentials_are_set() {
        Credentials credentials = new Credentials();
        credentials.setPassword("password");
        credentials.setUsername("username");

        ArtifactoryConfiguration configuration = new ArtifactoryConfiguration();
        configuration.setUrl("url");
        configuration.setAccessToken(random(32));
        configuration.setCredentials(credentials);

        assertThrows(ValidationException.class, () -> new Validator().validate(configuration));
    }

    @Test
    public void validate_should_succeed_if_access_token_is_set() {
        ArtifactoryConfiguration configuration = new ArtifactoryConfiguration();
        configuration.setUrl("url");
        configuration.setAccessToken(random(32));

        new Validator().validate(configuration);
    }

    @Test
    public void validate_should_succeed_if_credentials_is_set() {
        Credentials credentials = new Credentials();
        credentials.setPassword("password");
        credentials.setUsername("username");

        ArtifactoryConfiguration configuration = new ArtifactoryConfiguration();
        configuration.setUrl("url");
        configuration.setCredentials(credentials);

        new Validator().validate(configuration);
    }
}
