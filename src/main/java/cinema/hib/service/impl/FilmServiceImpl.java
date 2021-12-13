package cinema.hib.service.impl;

import cinema.hib.dto.mapper.FilmMapper;
import cinema.hib.dto.model.FilmDto;
import cinema.hib.model.Film;
import cinema.hib.repository.FilmRepository;
import cinema.hib.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;

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
    public String uploadDescriptionFromFile(String url) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(new ClassPathResource(url).getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    @Override
    public boolean downloadDescriptionToFile(String url, String description) throws IOException {

        if (url != null && description != null) {
            if (! new ClassPathResource(url).exists()) {
                System.out.println("create new file");
            }
            FileOutputStream fileOutputStream = new FileOutputStream(new ClassPathResource(url).getFile());
            fileOutputStream.write(description.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.close();
            return true;
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
}
