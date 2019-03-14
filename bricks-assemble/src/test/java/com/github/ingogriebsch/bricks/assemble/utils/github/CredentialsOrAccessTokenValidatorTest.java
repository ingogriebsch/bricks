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
package com.github.ingogriebsch.bricks.assemble.utils.github;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintValidatorContext;

import com.github.ingogriebsch.bricks.assemble.utils.github.GitHubConfiguration.Credentials;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CredentialsOrAccessTokenValidatorTest {

    @Test
    public void initialize_should_succeed_if_input_is_null() {
        new CredentialsOrAccessTokenValidator().initialize(null);
    }

    @Test
    public void initialize_should_succeed_if_input_is_given(@Mock CredentialsOrAccessToken credentialsOrAccessToken) {
        new CredentialsOrAccessTokenValidator().initialize(credentialsOrAccessToken);
    }

    @Test
    public void isValid_should_throw_exception_if_validatee_is_null(@Mock ConstraintValidatorContext context) {
        assertThrows(NullPointerException.class, () -> new CredentialsOrAccessTokenValidator().isValid(null, context));
    }

    @Test
    public void isValid_should_succeed_if_context_is_null() {
        new CredentialsOrAccessTokenValidator().isValid(new GitHubConfiguration(), null);
    }

    @Test
    public void isValid_should_fail_if_configuration_is_not_legal(@Mock ConstraintValidatorContext context) {
        CredentialsOrAccessTokenValidator validator = new CredentialsOrAccessTokenValidator();
        boolean valid = validator.isValid(new GitHubConfiguration(), context);
        assertThat(valid).isFalse();
    }

    @Test
    public void isValid_should_succeed_if_configuration_contains_access_token(@Mock ConstraintValidatorContext context) {
        GitHubConfiguration configuration = new GitHubConfiguration();
        configuration.setAccessToken(random(32));

        CredentialsOrAccessTokenValidator validator = new CredentialsOrAccessTokenValidator();
        boolean valid = validator.isValid(configuration, context);
        assertThat(valid).isTrue();
    }

    @Test
    public void isValid_should_succeed_if_configuration_contains_credentials(@Mock ConstraintValidatorContext context) {
        Credentials credentials = new Credentials();
        credentials.setUsername("username");
        credentials.setPassword("password");
        GitHubConfiguration configuration = new GitHubConfiguration();
        configuration.setCredentials(credentials);

        CredentialsOrAccessTokenValidator validator = new CredentialsOrAccessTokenValidator();
        boolean valid = validator.isValid(configuration, context);
        assertThat(valid).isTrue();
    }

    @Test
    public void
        isValid_should_fail_if_configuration_contains_credentials_and_access_token(@Mock ConstraintValidatorContext context) {
        Credentials credentials = new Credentials();
        credentials.setUsername("username");
        credentials.setPassword("password");
        GitHubConfiguration configuration = new GitHubConfiguration();
        configuration.setCredentials(credentials);
        configuration.setAccessToken(random(32));

        CredentialsOrAccessTokenValidator validator = new CredentialsOrAccessTokenValidator();
        boolean valid = validator.isValid(configuration, context);
        assertThat(valid).isFalse();
    }
}
