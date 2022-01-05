package cinema.hib.service.impl;

import cinema.hib.dto.mapper.SlotMapper;
import cinema.hib.dto.model.SlotDto;
import cinema.hib.dto.model.SlotDtoShort;
import cinema.hib.model.Film;
import cinema.hib.model.Slot;
import cinema.hib.repository.SlotRepository;
import cinema.hib.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

@Service
public class SlotServiceImpl implements SlotService {

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private FilmServiceImpl filmService;

    @Resource
    private SlotMapper slotMapper;

    @Override
    public SlotDto saveSlot(SlotDto slotDto) {
        if (slotDto != null) {
            long time = ChronoUnit.SECONDS.between(slotDto.getStartTime(), slotDto.getEndTime());
            if (time > slotDto.getFilm().getDuration()) {
                Slot before = new Slot();
                before.setFilm(slotMapper.toSlot(slotDto).getFilm());
                before.setEndTime(slotDto.getEndTime());
                before.setStartTime(slotDto.getStartTime());
                before.setDateOfFilm(slotDto.getDateOfFilm());
                Slot slot = slotRepository.save(before);
                slotRepository.findAll().stream().forEach(System.out::println);
                Film film = slot.getFilm();
                return slotMapper.toSlotDto(slot);
            }
        }
        return null;
    }

    @Override
    public boolean deleteSlot(@NotNull SlotDto slotDto) {
        if (slotRepository.findAll().contains(slotMapper.toSlot(slotDto))) {
            slotRepository.delete(slotMapper.toSlot(slotDto));
            return true;
        }
        return false;
    }

    @Override
    public SlotDto getSlotById(long id) {
        if (id > -1) {
            Slot slot = slotRepository.getById(id);
            return slotMapper.toSlotDto(slot);
        }
        return null;
    }

    @Override
    public SlotDto saveDtoShortToSlot(@NotNull SlotDtoShort slotDtoShort) throws NumberFormatException {

        try {
            LocalTime startTime = LocalTime.parse(slotDtoShort.getStartTime());
            LocalTime endTime = LocalTime.parse(slotDtoShort.getEndTime());

            if (endTime.isBefore(startTime)) {
                throw new NumberFormatException("end of slot time can`t be less then start time");
            }

            long time = Duration.between(startTime, endTime).getSeconds();

            if (time > filmService.getFilmById(slotDtoShort.getFilmId()).getDuration()) {
                Slot slot = slotRepository.save(slotMapper.toSlotFromShort(slotDtoShort));
                return slotMapper.toSlotDto(slot);
            } else {
                throw new NumberFormatException("slot time can`t be less then film duration");
            }
        } catch (DateTimeParseException e) {
            throw new NumberFormatException("can`t parse current time");
        }
    }
}
