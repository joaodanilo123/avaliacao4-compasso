package bolsa.compasso.api.partidos.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {GenderValidator.class})
public @interface ValidGender
{
    String message() default "deve ser DIREITA/ESQUERDA/CENTRO";
    Class[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}