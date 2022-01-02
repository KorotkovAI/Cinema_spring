package cinema.hib.dto.mapper;

import cinema.hib.dto.model.FilmDto;
import cinema.hib.dto.model.FilmDtoShort;
import cinema.hib.dto.model.FilmDtoToPage;
import cinema.hib.model.Film;
import cinema.hib.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilmMapper {

    @Autowired
    GenreMapper genreMapper;

    @Autowired
    GenreRepository genreRepository;

    public List<FilmDto> toFilmDtoList(@NotNull List<Film> films) {
        return films.stream().map(ent -> toFilmDto(ent)).collect(Collectors.toList());
    }

    public FilmDto toFilmDto(Film film) {
        //Here must add slots
        FilmDto result = new FilmDto();
        result.setId(film.getId());
        result.setDescription(film.getDescription());
        result.setAgeLimit(film.getAgeLimit());
        result.setName(film.getName());
        result.setRate(film.getRate());
        result.setDuration(film.getDuration());
        result.setGenres(genreMapper.toGenreDtoList(film.getGenres()));
        return result;
    }

    public Film toFilm(FilmDto filmDto) {
        //Here must add slots
        Film result = new Film();
        result.setAgeLimit(filmDto.getAgeLimit());
        result.setId(filmDto.getId());
        result.setDescription(filmDto.getDescription());
        result.setDuration((filmDto.getDuration()));
        result.setName(filmDto.getName());
        result.setRate(filmDto.getRate());
        result.setGenres(genreMapper.toGenreList(filmDto.getGenres()));
        return result;
    }

    public Film toFilmFromShort(FilmDtoShort filmDtoShort) {
        if (filmDtoShort != null) {
            Film result = new Film();
            result.setName(filmDtoShort.getName());
            result.setDuration(filmDtoShort.getDuration());
            result.setAgeLimit(filmDtoShort.getAgeLimit());
            result.setDescription(filmDtoShort.getDescription());
            result.setGenres(new ArrayList<>());
            return result;
        }
        return null;
    }

    public FilmDtoToPage toFilmDtoToPage(Film film) {
        if (film != null) {
            FilmDtoToPage filmDtoToPage = new FilmDtoToPage();
            filmDtoToPage.setId(film.getId());
            filmDtoToPage.setName(film.getName());
            filmDtoToPage.setAgeLimit(film.getAgeLimit());
            filmDtoToPage.setDuration(film.getDuration());
            String result = new String();

            if (film.getGenres() != null) {
                String genreNames = film.getGenres().stream().map(ent -> ent.getName()).collect(Collectors.toList()).toString();
                if (genreNames.length() > 2) {
                    result = genreNames.substring(1, genreNames.length() - 2);
                }
            }

            filmDtoToPage.setGenreNames(result);
            return filmDtoToPage;
        }
        return null;
    }
}
