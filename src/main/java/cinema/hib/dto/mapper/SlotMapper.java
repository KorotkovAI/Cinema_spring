package cinema.hib.dto.mapper;

import cinema.hib.dto.model.SlotDto;
import cinema.hib.dto.model.SlotDtoShort;
import cinema.hib.model.Slot;
import cinema.hib.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SlotMapper {

    @Autowired
    FilmMapper filmMapper;

    @Autowired
    FilmRepository filmRepository;

    public List<SlotDto> toSlotDtoList(List<Slot> slots) {
        if (slots != null) {
            return slots.stream().map(this::toSlotDto).collect(Collectors.toList());
        }
        return null;
    }

    public List<Slot> toSlotList(List<SlotDto> slotDtoList) {
        if (slotDtoList != null) {
            return slotDtoList.stream().map(this::toSlot).collect(Collectors.toList());
        }
        return null;
    }

    public SlotDto toSlotDto(Slot slot) {
        SlotDto result = new SlotDto();
        result.setId(slot.getId());
        result.setDateOfFilm(slot.getDateOfFilm());
        result.setEndTime(slot.getEndTime());
        result.setStartTime(slot.getStartTime());
        result.setFilm(filmMapper.toFilmDto(slot.getFilm()));
        return result;
    }

    public Slot toSlot(SlotDto slotDto) {
        Slot result = new Slot();
        result.setId(slotDto.getId());
        result.setDateOfFilm(slotDto.getDateOfFilm());
        result.setEndTime(slotDto.getEndTime());
        result.setStartTime(slotDto.getStartTime());
        result.setFilm(filmMapper.toFilm(slotDto.getFilm()));
        return result;
    }

    public Slot toSlotFromShort(SlotDtoShort slotDto) {

        if (slotDto != null) {
            Slot result = new Slot();
            result.setDateOfFilm(LocalDate.parse(slotDto.getDateOfFilm()));
            result.setEndTime(LocalTime.parse(slotDto.getEndTime()));
            result.setStartTime(LocalTime.parse(slotDto.getStartTime()));
            result.setFilm(filmRepository.getById(slotDto.getFilmId()));
            return result;
        }
        return null;
    }
}
