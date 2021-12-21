package cinema.hib.service;

import cinema.hib.dto.model.FilmPriceDto;
import cinema.hib.dto.model.SlotDto;

import java.util.List;

public interface FilmPriceService {

    List<FilmPriceDto> getBySlot(SlotDto slotDto);
}
