package cinema.hib.service.impl;

import cinema.hib.model.Film;
import cinema.hib.model.Genre;
import cinema.hib.repository.GenreRepository;
import cinema.hib.service.GenreService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre create(Genre genre) {
        if (genre != null) {
            return genreRepository.save(genre);
        }
        throw new NullPointerException("Genre cannot be 'null'");
    }

    @Override
    public Genre readById(int id) {
        return genreRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Genre with id " + id + " not found"));
    }

    @Override
    public Genre update(Genre genre) {
        if (genre != null) {
            readById(genre.getId());
            return genreRepository.save(genre);
        }
        throw new NullPointerException("Genre cannot be 'null'");
    }

    @Override
    public void delete(int id) {
        Genre genre = readById(id);
        genreRepository.delete(genre);
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    //TODO don't know how
    @Override
    public List<Film> getAllFilms(Genre genre) {
        /*
        if (genre != null) {
            readById(genre.getId());
            return genreRepository.findById(genre.getId()).;
        }

         */
        throw new NullPointerException("Genre cannot be 'null'");
    }


}
