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
        if (shedule != null) {
            SheduleDto result = new SheduleDto();
            result.setId(shedule.getId());
            result.setHallDtoName(shedule.getHallName());
            result.setSlotId(shedule.getSlotId());
            System.out.println("Result creating sheduleDto in mapper" + result);
            return result;
        }
        return null;
    }

    public Shedule toShedule(SheduleDto sheduleDto) {
        Shedule result = new Shedule();
        result.setId(sheduleDto.getId());
        result.setHallName(sheduleDto.getHallDtoName());
        result.setSlotId(sheduleDto.getSlotId());
        return result;
    }
}
