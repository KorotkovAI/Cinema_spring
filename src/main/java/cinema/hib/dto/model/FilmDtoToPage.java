package cinema.hib.dto.model;

import cinema.hib.model.AgeLimit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FilmDtoToPage {

    @Min(0)
    private long id;

    @NotBlank(message = "Film name can not be empty")
    @Size(min = 3, max = 100)
    private String name;

    @Min(1)
    private int duration;

    @NotNull
    private AgeLimit ageLimit;

    @NotNull
    private String genreNames;

}
