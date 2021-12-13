package cinema.hib.service;

import cinema.hib.dto.model.FilmDto;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface FilmService {


    Page<FilmDto> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    FilmDto getFilmById (long id);

    boolean updateFilmName (FilmDto filmDto);

    boolean updateFilmDuration (FilmDto filmDto);

    String uploadDescriptionFromFile (String url) throws IOException;

    boolean downloadDescriptionToFile (String url, String description) throws IOException;
}
