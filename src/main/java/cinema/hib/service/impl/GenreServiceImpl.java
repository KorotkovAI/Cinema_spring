package cinema.hib.service.impl;

import cinema.hib.dto.mapper.GenreMapper;
import cinema.hib.dto.model.GenreDto;
import cinema.hib.model.Genre;
import cinema.hib.repository.GenreRepository;
import cinema.hib.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Resource
    private GenreMapper genreMapper;

    @Override
    public Genre readById(int id) {
        return genreRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Genre with id " + id + " not found"));
    }

    @Override
    public GenreDto dtoReadById(int id) {
        return genreMapper.toGenreDto(readById(id));
    }

    @Override
    public List<GenreDto> getAll() {
        return genreMapper.toGenreDtoList(genreRepository.findAll());
    }
}
