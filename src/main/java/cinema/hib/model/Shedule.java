package cinema.hib.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "shedule")
@Getter
@Setter
public class Shedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "halls_name", referencedColumnName = "name", nullable = false)
    private String hallName;

    @JoinColumn(name = "slot_id", referencedColumnName = "id", nullable = false)
    private long slotId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shedule shedule = (Shedule) o;
        return slotId == shedule.slotId && hallName.equals(shedule.hallName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hallName, slotId);
    }

    @Override
    public String toString() {
        return "Shedule{" +
                "id=" + id +
                ", hallName='" + hallName + '\'' +
                ", slotId=" + slotId +
                '}';
    }
}
