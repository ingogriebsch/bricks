package com.github.ingogriebsch.bricks.model;

import static javax.validation.Validation.byDefaultProvider;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.Test;

public class StorageTest {

    private static final Validator validator = initializeValidator();

    @Test
    public void validation_should_fail_if_id_is_not_set() {
        Storage storage = new Storage();
        storage.setType("database");
        storage.setVendor("Postgresql");
        storage.setPersistent(true);

        Set<ConstraintViolation<Storage>> violations = validator.validate(storage);
        assertThat(violations).isNotNull().hasSize(1);
    }

    @Test
    public void validation_should_fail_if_type_is_not_set() {
        Storage storage = new Storage();
        storage.setId("id");
        storage.setVendor("Postgresql");
        storage.setPersistent(true);

        Set<ConstraintViolation<Storage>> violations = validator.validate(storage);
        assertThat(violations).isNotNull().hasSize(1);
    }

    @Test
    public void validation_should_fail_if_vendor_is_not_set() {
        Storage storage = new Storage();
        storage.setId("id");
        storage.setType("database");
        storage.setPersistent(true);

        Set<ConstraintViolation<Storage>> violations = validator.validate(storage);
        assertThat(violations).isNotNull().hasSize(1);
    }

    @Test
    public void validation_should_succeed_if_all_mandatory_properties_are_set() {
        Storage storage = new Storage();
        storage.setId("id");
        storage.setType("database");
        storage.setVendor("Postgresql");
        storage.setPersistent(true);

        Set<ConstraintViolation<Storage>> violations = validator.validate(storage);
        assertThat(violations).isNotNull().isEmpty();
    }

    private static Validator initializeValidator() {
        return byDefaultProvider().configure().messageInterpolator(new ParameterMessageInterpolator()).buildValidatorFactory()
            .getValidator();
    }
}
