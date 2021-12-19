package cinema.hib.service;

import cinema.hib.dto.model.HallDto;
import cinema.hib.dto.model.SheduleDto;
import cinema.hib.dto.model.SlotDto;

import java.time.LocalDate;
import java.util.List;

public interface SheduleService {

    SheduleDto getSheduleByHall(HallDto hallDto);

    List<SlotDto> getSlotsCurrentDate(SheduleDto sheduleDto, LocalDate date);

    boolean updateShedule(HallDto hallDto, SlotDto slotDto);
}
