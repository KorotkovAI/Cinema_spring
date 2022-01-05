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

    @ManyToOne
    @JoinColumn(name = "films_name", referencedColumnName = "name")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "shedule_id", referencedColumnName = "id")
    private Shedule shedule;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Slot slot = (Slot) object;
        return dateOfFilm.equals(slot.dateOfFilm) && startTime.equals(slot.startTime) && endTime.equals(slot.endTime) && film.equals(slot.film);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), dateOfFilm, startTime, endTime, film);
    }

    @Override
    public java.lang.String toString() {
        return "Slot{" +
                "id=" + id +
                ", dateOfFilm=" + dateOfFilm +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", film=" + film +
                '}';
    }
}
