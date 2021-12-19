package cinema.hib.service;

import cinema.hib.dto.model.HallDto;
import cinema.hib.model.Film;
import cinema.hib.model.Genre;

import java.util.List;

public interface HallService {

    List<HallDto> getAll();

    HallDto getHallById(int id);
}
