package bolsa.compasso.api.partidos.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {IdeologyValidator.class})
public @interface ValidIdeology
{
    String message() default "deve ser DIREITA/ESQUERDA/CENTRO";
    Class[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
