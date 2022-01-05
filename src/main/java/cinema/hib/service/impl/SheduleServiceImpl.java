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
        System.out.println("sheduleRepository.findAll()" + sheduleRepository.findAll());
        if (hallDto != null) {
            System.out.println("get into method");
            System.out.println(hallMapper.toHall(hallDto));
            Shedule shedule = sheduleRepository.getAllByHall(hallMapper.toHall(hallDto));
            System.out.println("result by getting shedule" + shedule);
            System.out.println("sheduleRepository.getAllByHall()!!!!! " + sheduleRepository.getAllByHall(hallMapper.toHall(hallDto)).getSlots());
            return sheduleMapper.toSheduleDto(shedule);
        }
        return null;
    }

    @Override
    public List<SlotDto> getSlotsCurrentDate(SheduleDto sheduleDto, LocalDate date) {
        List<SlotDto> slotDtos;
        System.out.println("sheduleDto.getSlotDtos()" + sheduleDto.getSlotDtos());
        System.out.println("sheduleRepository.findAll()" + sheduleRepository.findAll());
        if (sheduleDto.getSlotDtos() != null) {
            System.out.println("before stream" + sheduleDto.getSlotDtos());
            slotDtos = sheduleDto.getSlotDtos().stream().filter(ent -> ent.getDateOfFilm().equals(date)).collect(Collectors.toList());
        } else {
            slotDtos = new ArrayList<>();
        }
        System.out.println("filter by day" + slotDtos);
        LocalTime localTime = LocalTime.now();
        List<SlotDto> result;

        if (!slotDtos.isEmpty()) {
            result = slotDtos.stream().filter(ent -> ent.getStartTime().isAfter(localTime)).collect(Collectors.toList());
        } else {
            result = new ArrayList<>();
        }
        System.out.println("filter by start time" + result);
        return result;
    }

    @Override
    public boolean updateShedule(HallDto hallDto, SlotDto slotDto) {
        if (hallDto != null && slotDto != null) {
            Hall hall = hallMapper.toHall(hallDto);
            Slot slot = slotMapper.toSlot(slotDto);
            Shedule shedulesByHall = sheduleRepository.getShedulesByHall(hall);
            System.out.println("shedules by hall" + shedulesByHall);
            if (shedulesByHall == null) {
                shedulesByHall = new Shedule();
                shedulesByHall.setHall(hallMapper.toHall(hallDto));
            }
            System.out.println("shedulesByHall.getSlots()" + shedulesByHall.getSlots());
            if (shedulesByHall.getSlots() == null) {
                shedulesByHall.setSlots(new ArrayList<>());
            }
            System.out.println("shedulesByHall.getSlots()" + shedulesByHall.getSlots());
            shedulesByHall.getSlots().add(slot);
            System.out.println("shedule by hall updated" + shedulesByHall);
            sheduleRepository.save(shedulesByHall);
            System.out.println("shedule repository" + sheduleRepository.findAll());
            return true;
        }
        return false;
    }

    @Override
    public SheduleDto getSheduleBySlot(SlotDto slotDto) {
        if (slotDto != null) {
            Shedule shedule = sheduleRepository.getSheduleBySlotsContains(slotMapper.toSlot(slotDto));
            return sheduleMapper.toSheduleDto(shedule);
        }
        return null;
    }
}
