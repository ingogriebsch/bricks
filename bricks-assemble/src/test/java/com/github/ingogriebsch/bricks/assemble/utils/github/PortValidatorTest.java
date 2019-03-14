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

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.Integer.valueOf;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PortValidatorTest {

    @Test
    public void initialize_should_succeed_if_input_is_null() {
        new PortValidator().initialize(null);
    }

    @Test
    public void initialize_should_succeed_if_input_is_given(@Mock Port port) {
        new PortValidator().initialize(port);
    }

    @Test
    public void isValid_should_succeed_if_context_is_null() {
        new PortValidator().isValid(valueOf(23), null);
    }

    @Test
    public void isValid_should_fail_if_configuration_is_not_legal(@Mock ConstraintValidatorContext context) {
        PortValidator validator = new PortValidator();
        assertThat(validator.isValid(null, context)).isFalse();
        assertThat(validator.isValid(MIN_VALUE, context)).isFalse();
        assertThat(validator.isValid(MAX_VALUE, context)).isFalse();
    }

    @Test
    public void isValid_should_succeed_if_port_is_legal(@Mock ConstraintValidatorContext context) {
        PortValidator validator = new PortValidator();
        assertThat(validator.isValid(valueOf(23), context)).isTrue();
    }
}
