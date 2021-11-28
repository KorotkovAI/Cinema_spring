package cinema.hib.service;

import cinema.hib.dto.model.FilmDto;
import cinema.hib.dto.model.GenreDto;
import cinema.hib.dto.model.SlotDto;
import cinema.hib.model.Film;
import cinema.hib.model.Genre;
import cinema.hib.model.Slot;

import java.util.List;

public interface FilmService {
    FilmDto create(FilmDto filmDto);

    FilmDto readById(long id);

    FilmDto readByName(String name);

    FilmDto update(FilmDto filmDto);

    void delete(long id);

    List<Film> getAll();

    List<SlotDto> getAllSlots(FilmDto filmDto);

    List<GenreDto> getAllGenres(FilmDto filmDto);
}
