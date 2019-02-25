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
