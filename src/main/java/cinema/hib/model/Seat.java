package cinema.hib.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "seats")
@Getter
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank(message = "Raw number cannot be empty")
    @Setter
    private int raw;

    @NotNull
    @NotBlank(message = "Place number in raw cannot be empty")
    @Setter
    private int place;

    @NotNull
    @NotBlank(message = "Place number in raw cannot be empty")
    @Setter
    private double price;

    @Enumerated(EnumType.ORDINAL)
    @Setter
    private SeatType seatType;

    @Setter
    private String hall;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Seat seat = (Seat) object;
        return raw == seat.raw && place == seat.place && java.lang.Double.compare(seat.price, price) == 0
                && seatType.equals(seat.seatType) && hall.equals(seat.hall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), raw, place, price, seatType, hall);
    }

    @Override
    public java.lang.String toString() {
        return "Seat{" +
                "id=" + id +
                ", raw=" + raw +
                ", place=" + place +
                ", price=" + price +
                ", seatType=" + seatType +
                ", hall='" + hall + '\'' +
                '}';
    }
}
