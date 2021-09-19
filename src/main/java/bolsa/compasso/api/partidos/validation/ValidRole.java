package bolsa.compasso.api.partidos.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {RoleValidator.class})
public @interface ValidRole
{
    String message() default "cargo inválido";
    Class[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
