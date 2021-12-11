package cinema.hib.service;

import cinema.hib.dto.model.FilmDto;
import cinema.hib.dto.model.GenreDto;
import cinema.hib.dto.model.SlotDto;
import cinema.hib.model.Film;
import cinema.hib.model.Genre;
import cinema.hib.model.Slot;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FilmService {


    Page<FilmDto> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
