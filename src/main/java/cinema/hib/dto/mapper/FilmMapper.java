package cinema.hib.dto.mapper;

import cinema.hib.dto.model.FilmDto;
import cinema.hib.model.AgeLimit;
import cinema.hib.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilmMapper {

    @Autowired
    GenreMapper genreMapper;

    public List<FilmDto> toFilmDtoList(List<Film> films){
        return films.stream().map(ent->toFilmDto(ent)).collect(Collectors.toList());
    }

    public FilmDto toFilmDto(Film film) {
        //Here must add slots
        FilmDto result = new FilmDto();
        result.setId(film.getId());
        result.setDescription(film.getDescription());
        result.setAgeLimit(film.getAgeLimit().toString());
        result.setName(film.getName());
        result.setRate(film.getRate());
        result.setDuration(film.getDuration());
        result.setGenres(genreMapper.toGenreDtoList(film.getGenres()));
        return result;
    }

    public Film toFilm(FilmDto filmDto) {
        //Here must add slots
        Film result = new Film();
        result.setAgeLimit(AgeLimit.valueOf(filmDto.getAgeLimit()));
        result.setId(filmDto.getId());
        result.setDescription(filmDto.getDescription());
        result.setDuration((filmDto.getDuration()));
        result.setName(filmDto.getName());
        result.setRate(filmDto.getRate());
        result.setGenres(genreMapper.toGenreList(filmDto.getGenres()));
        return result;
    }
}
