package cinema.hib.service.impl;

import cinema.hib.dto.mapper.FilmMapper;
import cinema.hib.dto.model.FilmDto;
import cinema.hib.dto.model.GenreDto;
import cinema.hib.dto.model.SlotDto;
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
        Film currentFilm = filmRepository.getById(id);
        return mapper.toFilmDto(currentFilm);
    }

    @Override
    public boolean updateFilmName(FilmDto filmDto) {
        if (filmDto != null) {
            Film updatedFilm = filmRepository.save(mapper.toFilm(filmDto));
            System.out.println(updatedFilm.getName() + filmDto.getName());
            if (updatedFilm.getName().equals(mapper.toFilm(filmDto).getName())) {
                return true;
            }
        }
        return false;
    }
}
