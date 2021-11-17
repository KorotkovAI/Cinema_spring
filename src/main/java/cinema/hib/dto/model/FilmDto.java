package cinema.hib.dto.model;

import cinema.hib.model.AgeLimit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FilmDto {

    private String name;

    private int duration;

    private double rate;

    private AgeLimit ageLimit;

    private String description;

    private List<GenreDto> genres;
}
