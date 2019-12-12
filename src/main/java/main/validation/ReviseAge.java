package main.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;

@Documented
@Constraint(validatedBy = AgeConstraintValidator.class)
@Target({TYPE, FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReviseAge {
    int DEFAULT_AGE = 18;
    String DEFAULT_MESSAGE="Age which was given needs to be more than min";

    int min() default DEFAULT_AGE;

    String message() default DEFAULT_MESSAGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
