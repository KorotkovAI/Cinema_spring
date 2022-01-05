package cinema.hib.service.impl;

import cinema.hib.dto.mapper.FilmMapper;
import cinema.hib.dto.model.FilmDto;
import cinema.hib.dto.model.FilmDtoShort;
import cinema.hib.dto.model.FilmDtoToPage;
import cinema.hib.model.Film;
import cinema.hib.repository.FilmRepository;
import cinema.hib.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Resource
    private FilmMapper mapper;


    @Override
    public Page<FilmDtoToPage> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<Film> films = filmRepository.findAll(pageable);
        return films.map(ent -> mapper.toFilmDtoToPage(ent));
    }

    @Override
    public FilmDto getFilmById(long id) {
        if (id > -1) {
            Film currentFilm = filmRepository.getById(id);
            return mapper.toFilmDto(currentFilm);
        }
        return null;
    }

    @Override
    public FilmDto saveDtoShortToFilm(FilmDtoShort filmDto) {
        Film film = filmRepository.save(mapper.toFilmFromShort(filmDto));
        return mapper.toFilmDto(film);
    }

    @Override
    public FilmDto saveDtoToFilm(@NotNull FilmDto filmDto) {
        Film film = filmRepository.save(mapper.toFilm(filmDto));
        return mapper.toFilmDto(film);
    }

    @Override
    public List<FilmDto> findAll() {
        List<Film> films = filmRepository.findAll();
        return mapper.toFilmDtoList(films);
    }

    @Override
    public boolean deleteFilm(FilmDto filmDto) {
        if (filmDto != null) {
            filmRepository.delete(mapper.toFilm(filmDto));
            try {
                filmRepository.getById(filmDto.getId());
            } catch (EntityNotFoundException e) {
                return true;
            }
        }
        return false;
    }
}
