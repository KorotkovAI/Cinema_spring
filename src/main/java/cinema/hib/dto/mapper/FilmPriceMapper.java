package cinema.hib.dto.mapper;

import cinema.hib.dto.model.FilmPriceDto;
import cinema.hib.model.FilmPrice;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilmPriceMapper {

    @Resource
    SlotMapper slotMapper;

    @Resource
    SeatMapper seatMapper;

    public List<FilmPriceDto> toFilmPriceDtoList(List<FilmPrice> filmPriceList){
        return filmPriceList.stream().map(this::toFilmPriceDto).collect(Collectors.toList());
    }

    public List<FilmPrice> toFilmPriceList(List<FilmPriceDto> filmPriceDtoList) {
        return filmPriceDtoList.stream().map(this::toFilmPrice).collect(Collectors.toList());
    }

    public FilmPriceDto toFilmPriceDto(FilmPrice filmPrice) {
        FilmPriceDto result = new FilmPriceDto();
        result.setId(filmPrice.getId());
        result.setPrice(filmPrice.getPrice());
        result.setSeat(seatMapper.toSeatDtoList(filmPrice.getSeat()));
        result.setSlot(slotMapper.toSlotDto(filmPrice.getSlot()));
        return result;
    }

    public FilmPrice toFilmPrice(FilmPriceDto filmPriceDto) {
        FilmPrice result = new FilmPrice();
        result.setId(filmPriceDto.getId());
        result.setSlot(slotMapper.toSlot(filmPriceDto.getSlot()));
        result.setPrice(filmPriceDto.getPrice());
        result.setSeat(seatMapper.toSeatList(filmPriceDto.getSeat()));
        return result;
    }
}