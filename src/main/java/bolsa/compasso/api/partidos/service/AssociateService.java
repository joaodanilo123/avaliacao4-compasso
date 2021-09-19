package bolsa.compasso.api.partidos.service;

import bolsa.compasso.api.partidos.model.Associate;
import bolsa.compasso.api.partidos.model.Gender;
import bolsa.compasso.api.partidos.model.Role;
import bolsa.compasso.api.partidos.model.dto.AssociateDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AssociateService {
    public boolean validateRoleString(String toTest){
        try {
            Role role = Role.valueOf(toTest);
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    public boolean validateGenderString(String toTest){
        try {
            Gender gender = Gender.valueOf(toTest);
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    public AssociateDTO toAssociateDTO(Associate associate, ModelMapper modelMapper) {
        return modelMapper.map(associate, AssociateDTO.class);
    }
}
