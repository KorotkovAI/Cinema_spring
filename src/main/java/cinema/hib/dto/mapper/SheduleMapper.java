package cinema.hib.dto.mapper;

import cinema.hib.dto.model.SheduleDto;
import cinema.hib.model.Shedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SheduleMapper {

    @Autowired
    HallMapper hallMapper;

    @Autowired
    SlotMapper slotMapper;

    public SheduleDto toSheduleDto(Shedule shedule) {
        SheduleDto result = new SheduleDto();
        result.setId(shedule.getId());
        result.setHallDto(hallMapper.toHallDto(shedule.getHall()));
        result.setSlotDtos(slotMapper.toSlotDtoList(shedule.getSlots()));
        return result;
    }

    public Shedule toShedule(SheduleDto sheduleDto) {
        Shedule result = new Shedule();
        result.setId(sheduleDto.getId());
        result.setHall(hallMapper.toHall(sheduleDto.getHallDto()));
        result.setSlots(slotMapper.toSlotList(sheduleDto.getSlotDtos()));
        return result;
    }
}