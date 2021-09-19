package bolsa.compasso.api.partidos.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FormErrorDTO {
    private String message;
    private String field;
}
