package bolsa.compasso.api.partidos.model.dto;

import bolsa.compasso.api.partidos.model.Gender;
import bolsa.compasso.api.partidos.model.Role;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
public class AssociateDTO {
    private Long id;
    private String name;
    private Role role;
    private LocalDate birthDate;
    private Gender gender;
    private Long partyId;
}
