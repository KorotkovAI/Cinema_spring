package cinema.hib.dto.model;

import cinema.hib.model.Film;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SlotDto {

    private long id;

    private LocalDate dateOfFilm;

    private LocalTime startTime;

    private LocalTime endTime;

    private FilmDto film;

}
