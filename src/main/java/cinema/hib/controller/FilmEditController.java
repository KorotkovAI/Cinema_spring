package cinema.hib.controller;

import cinema.hib.dto.model.FilmDto;
import cinema.hib.service.impl.FilmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FilmEditController {

    @Autowired
    FilmServiceImpl filmService;

    @GetMapping("filmEdit/{id}")
    public String filmEditPage(@PathVariable(value = "id") long filmId, Model model) {
        FilmDto filmDto = filmService.getFilmById(filmId);
        model.addAttribute("film", filmDto);

        return "editFilm";
    }

    @GetMapping("filmEdit/name/{id}")
    public String filmEditName(@PathVariable(value = "id") long filmId, Model model) {
        FilmDto filmDto = filmService.getFilmById(filmId);

        model.addAttribute("paramForUpdate", "Name");
        model.addAttribute("valueForUpdate", filmDto.getName());
        return "editFilmName";
    }

    @PostMapping("filmEdit/name/{id}")
    public String filmEditNamePost(@PathVariable(value = "id") long filmId, @RequestParam(value = "updatedName") String updatedName, Model model) {
        String result = null;


        if (model.containsAttribute("exception")) {
            model.addAttribute("exception", null);
        }
        if (filmId != 0 && updatedName != null) {
            long id = filmId;
            FilmDto filmDto = filmService.getFilmById(filmId);
            filmDto.setName(updatedName);

            boolean status = false;
            try {
                status = filmService.updateFilmName(filmDto);
                System.out.println(status + "status");
            } catch (Exception e) {
                model.addAttribute("exception", e.getMessage());
            }

            if (status) {
                result = "redirect:/filmEdit/" + id;
            } else {
                model.addAttribute("paramForUpdate", "Name");
                model.addAttribute("valueForUpdate", filmDto.getName());
                result = "editFilmName";
            }
        } else {
            model.addAttribute("exception", "Bad params");
            result = "editFilmName";
        }

        return result;
    }


}
