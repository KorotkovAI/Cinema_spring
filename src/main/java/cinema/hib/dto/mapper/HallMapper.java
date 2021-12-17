package cinema.hib.dto.mapper;

import cinema.hib.dto.model.HallDto;
import cinema.hib.model.Hall;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HallMapper {

    public List<HallDto> toHallDtoList(List<Hall> halls){
        return halls.stream().map(this::toHallDto).collect(Collectors.toList());
    }

    public List<Hall> toHallList(List<HallDto> hallDtoList) {
        return hallDtoList.stream().map(this::toHall).collect(Collectors.toList());
    }

    public HallDto toHallDto(Hall hall) {
        HallDto result = new HallDto();
        result.setId(hall.getId());
        result.setName(hall.getName());
        return result;
    }

    public Hall toHall(HallDto hallDto) {
        Hall result = new Hall();
        result.setId(hallDto.getId());
        result.setName(hallDto.getName());
        return result;
    }
}
