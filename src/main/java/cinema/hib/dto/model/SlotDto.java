package cinema.hib.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SlotDto {

    @Min(0)
    private long id;

    private LocalDate dateOfFilm;

    private LocalTime startTime;

    private LocalTime endTime;

    private FilmDto film;

}
