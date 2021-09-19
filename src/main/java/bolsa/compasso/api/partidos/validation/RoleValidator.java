package bolsa.compasso.api.partidos.validation;

import bolsa.compasso.api.partidos.service.AssociateService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoleValidator implements ConstraintValidator<ValidRole, String> {

    @Autowired
    AssociateService associateService;

    @Override
    public void initialize(ValidRole constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {
        return associateService.validateRoleString(field);
    }
}
