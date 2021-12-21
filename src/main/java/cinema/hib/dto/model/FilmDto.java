package cinema.hib.dto.model;

import cinema.hib.model.AgeLimit;
import cinema.hib.model.Slot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FilmDto {

    @Min(0)
    private long id;

    @NotBlank(message = "Film name can not be empty")
    @Size(min = 3, max = 100)
    private String name;

    @Min(1)
    private int duration;

    @Min(0)
    private double rate;

    private String ageLimit;

    @Size(min = 10, max = 100)
    private String description;

    private List<GenreDto> genres;

    private List<SlotDto> slots;

}
