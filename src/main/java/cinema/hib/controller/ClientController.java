package cinema.hib.controller;

import cinema.hib.dto.model.FilmDto;
import cinema.hib.dto.model.HallDto;
import cinema.hib.service.impl.FilmServiceImpl;
import cinema.hib.service.impl.GenreServiceImpl;
import cinema.hib.service.impl.HallServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    FilmServiceImpl filmService;

    @Autowired
    GenreServiceImpl genreService;

    @Autowired
    HallServiceImpl hallService;

    @GetMapping("allFilms/")
    List<FilmDto> findAll(){
        return  filmService.findAll();
    }

    @GetMapping("allFilms/{id}")
    FilmDto findOne(@PathVariable Long id) {
        return filmService.getFilmById(id);
    }


    @GetMapping("allHalls/")
    List<HallDto> allHalls(){
        return hallService.getAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("allHalls/")
    HallDto createHall(@RequestBody HallDto hallDto) {
        return hallService.save(hallDto);
    }

}
