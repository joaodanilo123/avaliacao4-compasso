package bolsa.compasso.api.partidos.service;

import bolsa.compasso.api.partidos.model.Ideology;
import org.springframework.stereotype.Service;

@Service
public class PartyService {
    public boolean validateIdeologyString(String toTest){
        try {
            Ideology ideology = Ideology.valueOf(toTest);
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }
}
