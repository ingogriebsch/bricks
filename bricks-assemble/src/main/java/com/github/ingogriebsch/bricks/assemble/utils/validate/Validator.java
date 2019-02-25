package com.github.ingogriebsch.bricks.assemble.utils.validate;

import static java.lang.String.format;

import static javax.validation.Validation.byDefaultProvider;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import lombok.NonNull;

// FIXME Think about if this class is really necessary and if so where to put it.
public class Validator {

    private final javax.validation.Validator validator;

    public Validator() {
        validator = byDefaultProvider().configure().messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory().getValidator();
    }

    public void validate(@NonNull Object object) throws ValidationException {
        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(
                format("Object '%s' of type '%s' is not valid!", object, object.getClass().getName()), violations);
        }
    }

}
