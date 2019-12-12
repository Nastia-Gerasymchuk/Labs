package main.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;

public class Validator {
    private static javax.validation.Validator validator;

    public static <T> void validate(T object) {
        if (validator == null) {
            validator = Validation.buildDefaultValidatorFactory().getValidator();
        }
        Set<ConstraintViolation<T>> violations = validator.validate((object));
        if (!violations.isEmpty()) {
            String message="";
            violations.stream().map(ConstraintViolation::getMessage).forEach(m -> message.concat(m).concat(";"));
            throw new IllegalArgumentException(message);
        }
    }
}
