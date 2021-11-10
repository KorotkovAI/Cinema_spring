package cinema.hib.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

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
}
