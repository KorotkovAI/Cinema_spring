package cinema.hib.service;

import cinema.hib.dto.model.GenreDto;
import cinema.hib.model.Film;
import cinema.hib.model.Genre;

import java.util.List;

public interface GenreService {
    Genre create(Genre genre);

    Genre readById(int id);

    Genre update(Genre genre);

    void delete(int id);

    List<GenreDto> getAll();

    List<Film> getAllFilms(Genre genre);
}
