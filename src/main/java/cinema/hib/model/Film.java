package cinema.hib.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "films")
@Getter
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "The film Name cannot be empty")
    @Column(nullable = false, unique = true)
    @Setter
    @Size(min = 3, max = 100)
    private String name;

    @Setter
    private int duration;

    @Setter
    private double rate;

    @Enumerated(EnumType.ORDINAL)
    @Setter
    private AgeLimit ageLimit;

    @Setter
    @Size(min = 10, max = 100)
    private String description;

    @ManyToMany
    @Setter
    @JoinColumn(name = "genres_name", referencedColumnName = "name")
    private List<Genre> genres;

    @OneToMany
    @Setter
    @JoinColumn(name = "slot_id", referencedColumnName = "id")
    private List<Slot> slots;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Film film = (Film) object;
        return duration == film.duration && name.equals(film.name) && description.equals(film.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, duration, description);
    }

    @Override
    public java.lang.String toString() {
        return "Film{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                '}';
    }
}
