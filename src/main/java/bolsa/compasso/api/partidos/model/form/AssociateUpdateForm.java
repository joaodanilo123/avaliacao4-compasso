package bolsa.compasso.api.partidos.model.form;

import bolsa.compasso.api.partidos.model.Associate;
import bolsa.compasso.api.partidos.model.Gender;
import bolsa.compasso.api.partidos.model.Role;

public class AssociateUpdateForm extends AssociateForm {
    public void update(Associate associate){
        associate.setName(this.getName());
        associate.setGender(Gender.valueOf(this.getGender()));
        associate.setRole(Role.valueOf(this.getRole()));
        associate.setBirthDate(this.getBirthDate());
    }

}
