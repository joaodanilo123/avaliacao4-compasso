package bolsa.compasso.api.partidos.validation;

import bolsa.compasso.api.partidos.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdeologyValidator implements ConstraintValidator<ValidIdeology, String> {

    @Autowired
    PartyService partyService;

    @Override
    public void initialize(ValidIdeology constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {
        return partyService.validateIdeologyString(field);
    }
}
