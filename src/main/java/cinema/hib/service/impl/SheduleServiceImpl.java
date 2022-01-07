package cinema.hib.service.impl;

import cinema.hib.dto.mapper.SheduleMapper;
import cinema.hib.dto.model.HallDto;
import cinema.hib.dto.model.SheduleDto;
import cinema.hib.dto.model.SlotDto;
import cinema.hib.model.Hall;
import cinema.hib.model.Shedule;
import cinema.hib.repository.SheduleRepository;
import cinema.hib.service.SheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
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
    private HallServiceImpl hallService;

    @Resource
    private SlotServiceImpl slotService;

    @Resource
    private SheduleMapper sheduleMapper;

    @Override
    public SheduleDto getSheduleByHallName(String hallName) {
        if (hallName != null) {
            Hall hall = hallService.getHallByName(hallName);
            Shedule shedule = sheduleRepository.getByHallName(hall.getName());

            if (shedule != null) {
                return sheduleMapper.toSheduleDto(shedule);
            }
            throw new EntityNotFoundException("Shedule with hall " + hallName + " not found");
        }
        return null;
    }

    @Override
    public List<SlotDto> getSlotsCurrentDate(HallDto hallDto, LocalDate date) {

        if (hallDto != null && date != null) {
            List<SlotDto> slotDtos = new ArrayList<>();
            List<Long> slotsIdCurrentHall = sheduleRepository.findAll().stream().
                    filter(ent -> ent.getHallName().equals(hallDto.getName())).
                    map(Shedule::getSlotId).collect(Collectors.toList());
            for (Long idSlot : slotsIdCurrentHall) {
                slotDtos.add(slotService.getSlotById(idSlot));
            }

            LocalDate localDate = LocalDate.now();
            LocalTime localTime = LocalTime.now();
            List<SlotDto> result = new ArrayList<>();

            if (!slotDtos.isEmpty()) {
                if (date.isAfter(localDate)) {
                    result = slotDtos.stream().filter(ent -> ent.getDateOfFilm().equals(date)).collect(Collectors.toList());
                } else if (date.equals(localDate)) {
                    result = slotDtos.stream().filter(ent -> ent.getDateOfFilm().equals(date)).
                            filter(ent -> ent.getStartTime().isAfter(localTime)).collect(Collectors.toList());
                }
            }

            return result;
        }
        return null;
    }

    @Override
    public boolean updateShedule(String hallName, long slotId) {
        Shedule shedule = new Shedule();
        shedule.setHallName(hallName);
        shedule.setSlotId(slotId);
        if (!sheduleRepository.getAllByHallName(hallName).contains(shedule)) {
            sheduleRepository.save(shedule);
            return true;
        }
        return false;
    }

}
