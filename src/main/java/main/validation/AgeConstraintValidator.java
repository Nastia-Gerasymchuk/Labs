package main.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class AgeConstraintValidator implements ConstraintValidator<ReviseAge, LocalDate> {
    private ReviseAge age;

    @Override
    public void initialize(final ReviseAge constraintAnnotation) {
        age=constraintAnnotation;
    }


    @Override
    public boolean isValid(LocalDate dateBorn, ConstraintValidatorContext constraintValidatorContext) {
        return Period.between(dateBorn, LocalDate.now()).getYears()>=age.min();
    }
}

