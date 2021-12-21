package cinema.hib.dto.model;

import cinema.hib.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TicketDto {

    @Min(0)
    private long id;

    private User userId;

    private Film filmName;

    private Hall hallName;

    private Seat seatId;

    private Slot slot;

}
