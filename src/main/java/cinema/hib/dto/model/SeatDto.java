package cinema.hib.dto.model;

import cinema.hib.model.SeatType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SeatDto {

    private int raw;

    private int place;

    private double price;

    private SeatType seatType;

    private String hall;
}
