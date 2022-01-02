package cinema.hib.service.impl;

import cinema.hib.dto.mapper.FilmMapper;
import cinema.hib.dto.mapper.GenreMapper;
import cinema.hib.dto.model.GenreDto;
import cinema.hib.model.Film;
import cinema.hib.model.Genre;
import cinema.hib.repository.FilmRepository;
import cinema.hib.repository.GenreRepository;
import cinema.hib.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.Min;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Resource
    private GenreMapper genreMapper;

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
    public GenreDto dtoReadById(@Min(0) int id) {
        return genreMapper.toGenreDto(readById(id));
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
    public List<GenreDto> getAll() {
        return genreMapper.toGenreDtoList(genreRepository.findAll());
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
