package cinema.hib.dto.model;

import cinema.hib.model.Seat;
import cinema.hib.model.Slot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FilmPriceDto {

    private long id;

    private double price;

    private List<SeatDto> seat;

    private SlotDto slot;

}
