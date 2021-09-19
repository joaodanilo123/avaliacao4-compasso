package bolsa.compasso.api.partidos.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Table(name = "party")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Nullable
    private String initials;
    @Enumerated(EnumType.STRING)
    private Ideology ideology;
    private LocalDate foundation;
    @OneToMany(mappedBy = "party")
    @ToString.Exclude
    private List<Associate> associates;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Party party = (Party) o;
        return Objects.equals(id, party.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}