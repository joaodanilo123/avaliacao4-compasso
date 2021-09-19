package bolsa.compasso.api.partidos.model.form;

import bolsa.compasso.api.partidos.model.Ideology;
import bolsa.compasso.api.partidos.model.Party;
import bolsa.compasso.api.partidos.validation.ValidIdeology;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class PartyUpdateForm {
    @NotNull
    @Length(min = 2)
    private String name;
    private String initials;
    @NotNull
    @ValidIdeology
    private String ideology;
    @NotNull
    private LocalDate foundation;

    public void update(Party party){
        party.setName(this.name);
        party.setInitials(this.initials);
        party.setIdeology(Ideology.valueOf(this.ideology));
        party.setFoundation(this.foundation);
    }
}
