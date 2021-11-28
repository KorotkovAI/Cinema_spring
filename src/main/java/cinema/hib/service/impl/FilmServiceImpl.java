package cinema.hib.service.impl;

import cinema.hib.dto.model.FilmDto;
import cinema.hib.dto.model.GenreDto;
import cinema.hib.dto.model.SlotDto;
import cinema.hib.model.Film;
import cinema.hib.repository.FilmRepository;
import cinema.hib.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class FilmServiceImpl {

    @Autowired
    private FilmRepository filmRepository;



/*
    @Override
    public FilmDto create(FilmDto filmDto) {
        if (filmDto != null) {
            if (filmDto.getName() != null) {
                if (!filmRepository.findFilmByName(filmDto.getName()).isPresent()) {
                    return filmRepository.save(filmDto);
                }
                //TODO exception to throw
            }
            throw new NullPointerException("Film name cannot be 'null'");
        }
        throw new NullPointerException("Film cannot be 'null'");
    }

    @Override
    public FilmDto readById(long id) {
        return filmRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Film with id " + id + " not found"));
    }

    @Override
    public FilmDto readByName(String name) {
        if (name != null) {
            return filmRepository.findFilmByName(name).orElseThrow(
                    () -> new EntityNotFoundException("Film with name " + name + " not found"));
        }
        throw new NullPointerException("Name cannot be 'null'");
    }

    @Override
    public FilmDto update(FilmDto filmDto) {
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
*/
    //@Override
    public List<Film> getAll() {
        return filmRepository.findAll();
    }
//TODO I don`t know how
 /*
    @Override
    public List<SlotDto> getAllSlots(FilmDto filmDto) {
        if (film != null) {
            readById(film.getId());
            return filmRepository.findById(film.getId()).;
        }
        throw new NullPointerException("Film cannot be 'null'");
    }
//TODO not right solution
    @Override
    public List<GenreDto> getAllGenres(FilmDto filmDto) {
        if (film != null) {
            readById(film.getId());
            return film.getGenres();
        }
        throw new NullPointerException("Film cannot be 'null'");
    }

 */
}
