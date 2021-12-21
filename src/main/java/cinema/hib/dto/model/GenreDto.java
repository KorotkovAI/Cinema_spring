package cinema.hib.dto.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GenreDto {

    @Min(0)
    private int id;

    @NotBlank(message = "The film genre cannot be empty")
    @Size(min = 3, max = 50)
    private String name;

}
