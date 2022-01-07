package cinema.hib.service;

import cinema.hib.dto.model.HallDto;
import cinema.hib.dto.model.SheduleDto;
import cinema.hib.dto.model.SlotDto;

import java.time.LocalDate;
import java.util.List;

public interface SheduleService {

    SheduleDto getSheduleByHallName(String hallName);

    List<SlotDto> getSlotsCurrentDate(HallDto hallDto, LocalDate date);

    boolean updateShedule(String hallName, long slotId);

}
