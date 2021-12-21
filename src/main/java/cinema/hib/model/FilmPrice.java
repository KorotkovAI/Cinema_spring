package cinema.hib.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "priceFilm")
@Getter
@Setter
public class FilmPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Min(0)
    private double price;

    @OneToMany
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private List<Seat> seat;

    @ManyToOne
    @JoinColumn(name = "slot_id", referencedColumnName = "id")
    private Slot slot;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmPrice filmPrice = (FilmPrice) o;
        return id == filmPrice.id && Double.compare(filmPrice.price, price) == 0 && seat.equals(filmPrice.seat) && slot.equals(filmPrice.slot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, seat, slot);
    }

    @Override
    public String toString() {
        return "FilmPrice{" +
                "id=" + id +
                ", price=" + price +
                ", seat=" + seat +
                ", slot=" + slot +
                '}';
    }
}
