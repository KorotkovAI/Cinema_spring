package cinema.hib.service;

import cinema.hib.model.Film;
import cinema.hib.model.Genre;
import cinema.hib.model.Slot;

import java.util.List;

public interface FilmService {

    Film create(Film film);

    Film readById(long id);

    Film readByName(String name);

    Film update(Film film);

    void delete(long id);

    List<Film> getAll();

    List<Slot> getAllSlots(Film film);

    List<Genre> getAllGenres(Film film);
}
