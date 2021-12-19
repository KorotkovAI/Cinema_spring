package cinema.hib.service.impl;

import cinema.hib.dto.mapper.SlotMapper;
import cinema.hib.dto.model.SlotDto;
import cinema.hib.model.Film;
import cinema.hib.model.Slot;
import cinema.hib.repository.SlotRepository;
import cinema.hib.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.temporal.ChronoUnit;

@Service
public class SlotServiceImpl implements SlotService {

    @Autowired
    private SlotRepository slotRepository;

    @Resource
    private SlotMapper slotMapper;

    @Override
    public SlotDto saveSlot(SlotDto slotDto) throws Exception {
        System.out.println(slotDto == null);
        if (slotDto != null) {
            long time = ChronoUnit.SECONDS.between(slotDto.getStartTime(), slotDto.getEndTime());
            System.out.println(time + " " + slotDto.getFilm().getDuration());
            if (time > slotDto.getFilm().getDuration()) {
                System.out.println("get into method");
                Slot before = new Slot();
                before.setFilm(slotMapper.toSlot(slotDto).getFilm());
                before.setEndTime(slotDto.getEndTime());
                before.setStartTime(slotDto.getStartTime());
                before.setDateOfFilm(slotDto.getDateOfFilm());
                System.out.println("before" + before);
                Slot slot = slotRepository.save(before);
                slotRepository.findAll().stream().forEach(System.out::println);
                System.out.println("slot!!!!!!" + slot);
                Film film = slot.getFilm();
                System.out.println("film" + film);
                return slotMapper.toSlotDto(slot);
            } else {
                throw new Exception("slot time can`t be less then film duration");
            }
        }
        return null;
    }

    @Override
    public boolean deleteSlot(SlotDto slotDto) throws Exception {
        if (slotDto != null) {
            if (slotRepository.findAll().contains(slotMapper.toSlot(slotDto))) {
                slotRepository.delete(slotMapper.toSlot(slotDto));
                return true;
            } else {
                throw new Exception("Such slot is absent in database");
            }
        }
        return false;
    }
}
