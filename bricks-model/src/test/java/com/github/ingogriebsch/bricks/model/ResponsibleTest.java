package com.github.ingogriebsch.bricks.model;

import static javax.validation.Validation.byDefaultProvider;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.Test;

public class ResponsibleTest {

    @Test
    public void validation_should_fail_if_name_is_not_set() {
        Responsible responsible = new Responsible();
        responsible.setEmail("email@example.com");

        Validator validator = initializeValidator();
        Set<ConstraintViolation<Responsible>> violations = validator.validate(responsible);

        assertThat(violations).isNotNull().hasSize(1);
    }

    @Test
    public void validation_should_fail_if_type_is_not_set() {
        Responsible responsible = new Responsible();
        responsible.setName("Max Mustermann");

        Validator validator = initializeValidator();
        Set<ConstraintViolation<Responsible>> violations = validator.validate(responsible);

        assertThat(violations).isNotNull().hasSize(1);
    }

    @Test
    public void validation_should_succeed_if_all_mandatory_properties_are_set() {
        Responsible responsible = new Responsible();
        responsible.setName("Max Mustermann");
        responsible.setEmail("email@example.com");

        Validator validator = initializeValidator();
        Set<ConstraintViolation<Responsible>> violations = validator.validate(responsible);

        assertThat(violations).isNotNull().isEmpty();
    }

    private static Validator initializeValidator() {
        return byDefaultProvider().configure().messageInterpolator(new ParameterMessageInterpolator()).buildValidatorFactory()
            .getValidator();
    }
}
