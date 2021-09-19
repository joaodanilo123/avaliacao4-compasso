package bolsa.compasso.api.partidos.model.form;

import bolsa.compasso.api.partidos.model.Gender;
import bolsa.compasso.api.partidos.model.Role;
import bolsa.compasso.api.partidos.validation.ValidGender;
import bolsa.compasso.api.partidos.validation.ValidRole;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class AssociateForm {
    @NotNull
    @Length(min = 2)
    private String name;
    @NotNull
    @ValidRole
    private String role;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    @ValidGender
    private String gender;
}
