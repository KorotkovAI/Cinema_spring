package cinema.hib.service;

import cinema.hib.model.Film;
import cinema.hib.model.Genre;
import cinema.hib.model.Slot;

import java.util.List;

public interface GenreService {

    Genre create(Genre genre);

    Genre readById(long id);

    Genre readByName(String name);

    Genre update(Genre genre);

    void delete(long id);

    void delete(String name);

    List<Genre> getAll();

    List<Film> getAllFilms(Genre Genre);
}
