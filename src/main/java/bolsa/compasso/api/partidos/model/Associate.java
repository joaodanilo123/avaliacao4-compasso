package bolsa.compasso.api.partidos.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Table(name = "associate")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Associate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @ManyToOne
    private Party party;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Associate associate = (Associate) o;
        return Objects.equals(id, associate.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}