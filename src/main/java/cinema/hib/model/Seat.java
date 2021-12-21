package cinema.hib.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "seats")
@Getter
@Setter
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Min(0)
    private int raw;

    @Column(nullable = false)
    @Min(0)
    private int place;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private SeatType seatType;

    @ManyToOne
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    private Hall hall;

    @ManyToOne
    @JoinColumn(name = "filmPrice_id", referencedColumnName = "id")
    private FilmPrice filmPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return id == seat.id && raw == seat.raw && place == seat.place && seatType == seat.seatType && hall.equals(seat.hall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, raw, place, seatType, hall);
    }


    @ManyToOne(optional = false)
    private FilmPrice filmPrices;

    public FilmPrice getFilmPrices() {
        return filmPrices;
    }

    public void setFilmPrices(FilmPrice filmPrices) {
        this.filmPrices = filmPrices;
    }

    @ManyToOne(optional = false)
    private FilmPrice filmPrices2;

    public FilmPrice getFilmPrices2() {
        return filmPrices2;
    }

    public void setFilmPrices2(FilmPrice filmPrices2) {
        this.filmPrices2 = filmPrices2;
    }
}
