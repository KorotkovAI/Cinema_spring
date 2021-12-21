package cinema.hib.service;

import cinema.hib.dto.model.SlotDto;

public interface SlotService {

    SlotDto saveSlot (SlotDto slotDto) throws Exception;

    boolean deleteSlot (SlotDto slotDto) throws Exception;

    SlotDto getSlotById (long id);

}
