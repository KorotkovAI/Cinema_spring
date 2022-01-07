package cinema.hib.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SlotDtoShort {

    @NotNull
    @Pattern(regexp = "^((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$",
            message = "date must have format YYYY-MM-DD")
    private String dateOfFilm;

    @NotNull
    @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]", message = "time must have format hh:mm")
    private String startTime;

    @NotNull
    @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]", message = "time must have format hh:mm")
    private String endTime;

    @NotNull
    private String filmName;
}
