package cinema.hib.dto.mapper;

import cinema.hib.dto.model.SeatDto;
import cinema.hib.model.Seat;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SeatMapper {

    @Resource
    HallMapper hallMapper;

    public List<SeatDto> toSeatDtoList(List<Seat> seats){
        return seats.stream().map(this::toSeatDto).collect(Collectors.toList());
    }

    public List<Seat> toSeatList(List<SeatDto> seatDtoList) {
        return seatDtoList.stream().map(this::toSeat).collect(Collectors.toList());
    }

    public SeatDto toSeatDto(Seat seat) {
        SeatDto result = new SeatDto();
        result.setId(seat.getId());
        result.setPlace(seat.getPlace());
        result.setRaw(seat.getRaw());
        result.setSeatType(seat.getSeatType());
        result.setHall(hallMapper.toHallDto(seat.getHall()));
        return result;
    }

    public Seat toSeat(SeatDto seatDto) {
        Seat result = new Seat();
        result.setId(seatDto.getId());
        result.setPlace(seatDto.getPlace());
        result.setRaw(seatDto.getRaw());
        result.setSeatType(seatDto.getSeatType());
        result.setHall(hallMapper.toHall(seatDto.getHall()));
        return result;
    }
}
