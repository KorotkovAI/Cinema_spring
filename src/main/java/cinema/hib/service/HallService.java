package cinema.hib.service;

import cinema.hib.model.Film;
import cinema.hib.model.Genre;

import java.util.List;

public interface HallService {

    Genre create(Genre genre);

    Genre readById(int id);

    Genre update(Genre genre);

    void delete(int id);

    List<Genre> getAll();

    List<Film> getAllFilms(Genre genre);
}
