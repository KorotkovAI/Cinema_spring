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
public class HallDto {

    @Min(0)
    private int id;

    @NotBlank(message = "Name of the hall cannot be empty")
    @Size(min = 3, max = 25)
    private String name;
}
