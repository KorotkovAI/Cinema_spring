package cinema.hib.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SheduleDto {

    @Min(0)
    private long id;

    private HallDto hallDto;

    private List<SlotDto> slotDtos;
}
