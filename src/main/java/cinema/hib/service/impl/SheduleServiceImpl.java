package cinema.hib.service.impl;

import cinema.hib.dto.mapper.HallMapper;
import cinema.hib.dto.mapper.SheduleMapper;
import cinema.hib.dto.mapper.SlotMapper;
import cinema.hib.dto.model.HallDto;
import cinema.hib.dto.model.SheduleDto;
import cinema.hib.dto.model.SlotDto;
import cinema.hib.model.Hall;
import cinema.hib.model.Shedule;
import cinema.hib.model.Slot;
import cinema.hib.repository.SheduleRepository;
import cinema.hib.service.SheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SheduleServiceImpl implements SheduleService {

    @Autowired
    private SheduleRepository sheduleRepository;

    @Resource
    private SheduleMapper sheduleMapper;

    @Resource
    private HallMapper hallMapper;

    @Resource
    private SlotMapper slotMapper;

    @Override
    public SheduleDto getSheduleByHall(HallDto hallDto) {
        SheduleDto result = null;

        if (hallDto != null) {
        Shedule shedule = sheduleRepository.getShedulesByHall(hallMapper.toHall(hallDto));
        result = sheduleMapper.toSheduleDto(shedule);
        }
        return result;
    }

    @Override
    public List<SlotDto> getSlotsCurrentDate(SheduleDto sheduleDto, LocalDate date) {
        List<SlotDto> slotDtos;
        if (sheduleDto.getSlotDtos() != null) {
            slotDtos = sheduleDto.getSlotDtos().stream().filter(ent -> ent.getDateOfFilm().equals(date)).collect(Collectors.toList());
        } else {
            slotDtos = new ArrayList<>();
        }

        LocalTime localTime = LocalTime.now();
        List<SlotDto> result;

        if (!slotDtos.isEmpty()) {
            result = slotDtos.stream().filter(ent -> ent.getStartTime().isAfter(localTime)).collect(Collectors.toList());
        } else {
            result = new ArrayList<>();
        }
        return  result;
    }

    @Override
    public boolean updateShedule(HallDto hallDto, SlotDto slotDto) {
        if (hallDto != null && slotDto != null) {
            Hall hall = hallMapper.toHall(hallDto);
            Slot slot = slotMapper.toSlot(slotDto);
            Shedule shedulesByHall = sheduleRepository.getShedulesByHall(hall);

            List<Slot> slotsCurrentDay = shedulesByHall.getSlots().stream().
                    filter(ent -> ent.getDateOfFilm().equals(slot.getDateOfFilm())).collect(Collectors.toList());
            slotsCurrentDay.forEach(System.out::println);
            shedulesByHall.getSlots().add(slot);
            return true;
        }
        return false;
    }
}
