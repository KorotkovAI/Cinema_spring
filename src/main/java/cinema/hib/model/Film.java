package cinema.hib.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "films")
@Getter
@Setter
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "The film Name cannot be empty")
    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 100)
    private String name;

    @Min(1)
    @Column(nullable = false)
    private int duration;

    @Min(0)
    private double rate;

    @Enumerated(EnumType.ORDINAL)
    private AgeLimit ageLimit;

    @Column(nullable = false)
    @Size(min = 10, max = 100)
    private String description;

    @ManyToMany
    @JoinColumn(name = "genres_name", referencedColumnName = "name")
    private List<Genre> genres;

    @OneToMany
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
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", rate=" + rate +
                ", ageLimit=" + ageLimit +
                ", description='" + description + '\'' +
                '}';
    }
}
