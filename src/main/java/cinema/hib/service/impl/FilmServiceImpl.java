package cinema.hib.service.impl;

import cinema.hib.dto.mapper.FilmMapper;
import cinema.hib.dto.model.FilmDto;
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
import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Resource
    private FilmMapper mapper;


    @Override
    public Page<FilmDto> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<Film> films = filmRepository.findAll(pageable);
        return films.map(ent -> mapper.toFilmDto(ent));
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
    public boolean updateFilmName(FilmDto filmDto) {
        if (filmDto != null) {
            Film updatedFilm = filmRepository.save(mapper.toFilm(filmDto));
            if (updatedFilm.getName().equals(filmDto.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateFilmDuration(FilmDto filmDto) {
        if (filmDto != null) {
            Film updatedFilm = filmRepository.save(mapper.toFilm(filmDto));
            if (updatedFilm.getDuration() == filmDto.getDuration()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateFilmAgeLimit(FilmDto filmDto) {
        if (filmDto != null) {
            Film updatedFilm = filmRepository.save(mapper.toFilm(filmDto));
            if (updatedFilm.getAgeLimit().equals(filmDto.getAgeLimit())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public FilmDto saveFilm(FilmDto filmDto) {
        if (filmDto != null) {
            Film film = filmRepository.save(mapper.toFilm(filmDto));
            return mapper.toFilmDto(film);
        }
        return null;
    }

    @Override
    public List<FilmDto> findAll() {
        List<Film> films = filmRepository.findAll();
        return mapper.toFilmDtoList(films);
    }
}
