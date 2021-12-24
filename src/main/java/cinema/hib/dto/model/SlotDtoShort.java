package cinema.hib.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SlotDtoShort {

    @NotNull
    private String dateOfFilm;

    @NotNull
    private String startTime;

    @NotNull
    private String endTime;

    @Min(0)
    private long filmId;
}
