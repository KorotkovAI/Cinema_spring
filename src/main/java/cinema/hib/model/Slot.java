package cinema.hib.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "slots")
@Getter
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @NotNull(message = "The date of film cannot be empty")
    @Setter
    private LocalDate dateOfFilm;

    @NotBlank
    @NotNull(message = "The time start of film cannot be empty")
    @Setter
    private LocalTime startTime;

    @NotBlank
    @NotNull(message = "The end time of film cannot be empty")
    @Setter
    private LocalTime endTime;

    @ManyToOne
    @Setter
    @JoinColumn(name = "films_name", referencedColumnName = "name")
    private Film film;

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
