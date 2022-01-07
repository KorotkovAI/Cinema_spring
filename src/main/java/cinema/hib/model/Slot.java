package cinema.hib.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "slots")
@Getter
@Setter
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDate dateOfFilm;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @JoinColumn(name = "films_name", table = "films", referencedColumnName = "name", nullable = false)
    private String filmName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slot slot = (Slot) o;
        return id == slot.id && dateOfFilm.equals(slot.dateOfFilm) && startTime.equals(slot.startTime) && endTime.equals(slot.endTime) && filmName.equals(slot.filmName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfFilm, startTime, endTime, filmName);
    }
}
