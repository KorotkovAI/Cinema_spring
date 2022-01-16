package cinema.hib.service;

import cinema.hib.dto.model.GenreDto;
import cinema.hib.model.Genre;

import java.util.List;

public interface GenreService {

    Genre readById(int id);

    List<GenreDto> getAll();

    GenreDto dtoReadById(int id);
}
