package cinema.hib.service;

import cinema.hib.dto.model.FilmDto;
import org.springframework.data.domain.Page;

public interface FilmService {


    Page<FilmDto> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    FilmDto getFilmById (long id);

    boolean updateFilmName (FilmDto filmDto);

    boolean updateFilmDuration (FilmDto filmDto);

    boolean updateFilmAgeLimit(FilmDto filmDto);

    FilmDto saveFilm (FilmDto filmDto);
}
