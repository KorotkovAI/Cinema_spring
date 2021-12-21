package cinema.hib.service.impl;

import cinema.hib.dto.mapper.FilmPriceMapper;
import cinema.hib.dto.mapper.SlotMapper;
import cinema.hib.dto.model.FilmPriceDto;
import cinema.hib.dto.model.SlotDto;
import cinema.hib.model.FilmPrice;
import cinema.hib.repository.FilmPriceRepository;
import cinema.hib.service.FilmPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FilmPriceServiceImpl implements FilmPriceService {

    @Autowired
    FilmPriceRepository filmPriceRepository;

    @Resource
    SlotMapper slotMapper;

    @Resource
    FilmPriceMapper filmPriceMapper;

    @Override
    public List<FilmPriceDto> getBySlot(SlotDto slotDto) {
        List<FilmPrice> result = filmPriceRepository.getAllBySlotsContains(slotMapper.toSlot(slotDto));
        return filmPriceMapper.toFilmPriceDtoList(result);
    }
}
