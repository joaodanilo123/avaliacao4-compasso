package bolsa.compasso.api.partidos.validation;

import bolsa.compasso.api.partidos.service.AssociateService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<ValidGender, String> {

    @Autowired
    AssociateService associateService;

    @Override
    public void initialize(ValidGender constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {
        return associateService.validateGenderString(field);
    }
}
