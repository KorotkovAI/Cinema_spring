package cinema.hib.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shedule")
@Getter
public class Shedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @OneToOne
    private Hall hall;

    @Setter
    @OneToMany
    private List<Slot> slots;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Shedule shedule = (Shedule) object;
        return hall.equals(shedule.hall) && slots.equals(shedule.slots);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hall, slots);
    }

    @Override
    public java.lang.String toString() {
        return "Shedule{" +
                "id=" + id +
                ", hall=" + hall +
                '}';
    }
}
