package cinema.hib.service;

import cinema.hib.dto.model.SlotDto;
import cinema.hib.dto.model.SlotDtoShort;

import javax.validation.constraints.NotNull;

public interface SlotService {

    SlotDto saveSlot (SlotDto slotDto) throws Exception;

    boolean deleteSlot (@NotNull SlotDto slotDto);

    SlotDto getSlotById (long id);

    SlotDto saveDtoShortToSlot(@NotNull SlotDtoShort slotDtoShort);

}
