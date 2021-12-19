package cinema.hib.dto.mapper;

import cinema.hib.dto.model.SlotDto;
import cinema.hib.model.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SlotMapper {

    @Autowired
    FilmMapper filmMapper;

    public List<SlotDto> toSlotDtoList(List<Slot> slots){
        return slots.stream().map(this::toSlotDto).collect(Collectors.toList());
    }

    public List<Slot> toSlotList(List<SlotDto> slotDtoList) {
        return slotDtoList.stream().map(this::toSlot).collect(Collectors.toList());
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
}
