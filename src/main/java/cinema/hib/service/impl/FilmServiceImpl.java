package cinema.hib.service.impl;

import cinema.hib.model.Film;
import cinema.hib.model.Genre;
import cinema.hib.model.Slot;
import cinema.hib.repository.FilmRepository;
import cinema.hib.service.FilmService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Film create(Film film) {
        if (film != null) {
            return filmRepository.save(film);
        }
        throw new NullPointerException("Film cannot be 'null'");
    }

    @Override
    public Film readById(long id) {
        return filmRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Film with id " + id + " not found"));
    }

    @Override
    public Film readByName(String name) {
        if (name != null) {
            return filmRepository.findFilmByName(name).orElseThrow(
                    () -> new EntityNotFoundException("Film with name " + name + " not found"));
        }
        throw new NullPointerException("Name cannot be 'null'");
    }

    @Override
    public Film update(Film film) {
        if (film != null) {
            readById(film.getId());
            return filmRepository.save(film);
        }
        throw new NullPointerException("Film cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        Film film = readById(id);
        filmRepository.delete(film);
    }

    @Override
    public List<Film> getAll() {
        return filmRepository.findAll();
    }
//TODO I don`t know how
    @Override
    public List<Slot> getAllSlots(Film film) {
        if (film != null) {
            readById(film.getId());
            return filmRepository.findById(film.getId()).;
        }
        throw new NullPointerException("Film cannot be 'null'");
    }
//TODO not right solution
    @Override
    public List<Genre> getAllGenres(Film film) {
        if (film != null) {
            readById(film.getId());
            return film.getGenres();
        }
        throw new NullPointerException("Film cannot be 'null'");
    }
}
