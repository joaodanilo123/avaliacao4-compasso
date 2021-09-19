package bolsa.compasso.api.partidos.model.dto;

import bolsa.compasso.api.partidos.model.Associate;
import bolsa.compasso.api.partidos.model.Ideology;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PartyDTO {
    private Long id;
    private String name;
    private String initials;
    private Ideology ideology;
    private LocalDate foundation;
}
