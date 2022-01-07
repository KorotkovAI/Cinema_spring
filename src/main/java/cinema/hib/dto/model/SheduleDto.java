package cinema.hib.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class SheduleDto {

    private long id;

    private String hallDtoName;

    private long SlotId;
}
