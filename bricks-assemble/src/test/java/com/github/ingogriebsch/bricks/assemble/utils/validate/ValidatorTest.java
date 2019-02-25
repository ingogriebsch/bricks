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
package com.github.ingogriebsch.bricks.assemble.utils.validate;

import javax.validation.ConstraintViolationException;

import org.hibernate.validator.constraints.NotBlank;
import org.junit.Test;

import lombok.Value;

public class ValidatorTest {

    private final static Validator validator = new Validator();

    @Test(expected = NullPointerException.class)
    public void validate_should_throw_exception_if_validatee_is_null() {
        validator.validate(null);
    }

    @Test(expected = ConstraintViolationException.class)
    public void validate_should_throw_exception_if_validatee_is_not_valid() {
        validator.validate(new Validatee(null));
    }

    @Test
    public void validate_should_succeed_if_validatee_is_valid() {
        validator.validate(new Validatee("Some name"));
    }

    @Value
    private static class Validatee {

        @NotBlank
        private String name;

    }
}
