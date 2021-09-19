package bolsa.compasso.api.partidos.model.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AssociationForm {
    @NotNull
    Long associateId;
    @NotNull
    Long partyId;
}
