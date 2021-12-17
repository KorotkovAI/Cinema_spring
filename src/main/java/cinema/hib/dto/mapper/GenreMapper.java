package cinema.hib.dto.mapper;

import cinema.hib.dto.model.GenreDto;
import cinema.hib.model.Genre;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreMapper {

    public List<GenreDto> toGenreDtoList(List<Genre> genres){
        return genres.stream().map(ent->toGenreDto(ent)).collect(Collectors.toList());
    }

    public List<Genre> toGenreList(List<GenreDto> genreDtoList) {
        return genreDtoList.stream().map(ent->toGenre(ent)).collect(Collectors.toList());
    }

    public GenreDto toGenreDto(Genre genre) {
        GenreDto result = new GenreDto();
        result.setId(genre.getId());
        result.setName(genre.getName());
        return result;
    }

    public Genre toGenre(GenreDto genreDto) {
        Genre result = new Genre();
        result.setId(genreDto.getId());
        result.setName(genreDto.getName());
        return result;
    }
}
