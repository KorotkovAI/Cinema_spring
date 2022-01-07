package cinema.hib.service;

import cinema.hib.dto.model.FilmDto;
import cinema.hib.dto.model.FilmDtoShort;
import cinema.hib.dto.model.FilmDtoToPage;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FilmService {


    Page<FilmDtoToPage> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    FilmDto getFilmById (long id);

    FilmDto saveDtoShortToFilm (FilmDtoShort filmDto);

    FilmDto saveDtoToFilm (FilmDto filmDto);

    List<FilmDto> findAll();

    boolean deleteFilm(FilmDto filmDto);

    FilmDto getFilmByName(String filmName);
}
