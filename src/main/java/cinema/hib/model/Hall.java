package cinema.hib.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "halls")
@Getter
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @NotBlank(message = "Name of the hall cannot be empty")
    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 25)
    private String name;

    @OneToMany
    @Setter
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private List <Seat> seats;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Hall hall = (Hall) object;
        return name.equals(hall.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public java.lang.String toString() {
        return "Hall{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
