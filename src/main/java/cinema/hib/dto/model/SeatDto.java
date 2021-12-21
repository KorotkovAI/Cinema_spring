package cinema.hib.dto.model;

import cinema.hib.model.SeatType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SeatDto {

    @Min(0)
    private int id;

    @Min(0)
    private int raw;

    @Min(0)
    private int place;

    private SeatType seatType;

    private HallDto hall;
}
