package cinema.hib.controller;

import cinema.hib.dto.model.FilmDto;
import cinema.hib.model.AgeLimit;
import cinema.hib.service.impl.FilmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class FilmEditController {

    @Autowired
    FilmServiceImpl filmService;
/*
    @GetMapping("filmEdit/{id}")
    public String filmEditPage(@PathVariable(value = "id") long filmId, Model model) {
        FilmDto filmDto = filmService.getFilmById(filmId);
        String filmDescription;

        try {
            filmDescription = filmService.uploadDescriptionFromFile(filmDto.getDescription());
        } catch (IOException e) {
            filmDescription = "file not found";
        }

        model.addAttribute("filmdescription", filmDescription);
        model.addAttribute("film", filmDto);

        return "editFilm";
    }

 */
    @GetMapping("filmEdit/name/{id}")
    public String filmEditName(@PathVariable(value = "id") long filmId, Model model) {
        FilmDto filmDto = filmService.getFilmById(filmId);

        model.addAttribute("exception", null);
        model.addAttribute("paramForUpdate", "Name");
        model.addAttribute("valueForUpdate", filmDto.getName());
        return "editFilmName";
    }

    @PostMapping("filmEdit/name/{id}")
    public String filmEditNamePost(@PathVariable(value = "id") long filmId, @RequestParam(value = "updatedName") String updatedName, Model model) {
        String result = "editFilmName";


        if (model.containsAttribute("exception")) {
            model.addAttribute("exception", null);
        }
        if (filmId > 0 && updatedName != null) {
            FilmDto filmDto = filmService.getFilmById(filmId);
            filmDto.setName(updatedName);

            boolean status = false;
            try {
                status = filmService.updateFilmName(filmDto);
            } catch (Exception e) {
                model.addAttribute("exception", e.getMessage());
            }

            if (status) {
                result = "redirect:/filmEdit/" + filmId;
            } else {
                model.addAttribute("paramForUpdate", "Name");
                model.addAttribute("valueForUpdate", filmDto.getName());
            }
        } else {
            model.addAttribute("paramForUpdate", "Name");
            model.addAttribute("exception", "Bad params");
        }

        return result;
    }

    @GetMapping("filmEdit/duration/{id}")
    public String filmEditDuration(@PathVariable(value = "id") long filmId, Model model) {
        FilmDto filmDto = filmService.getFilmById(filmId);

        model.addAttribute("exception", null);
        model.addAttribute("paramForUpdate", "Duration");
        model.addAttribute("valueForUpdate", filmDto.getDuration());
        return "editFilmDuration";
    }

    @PostMapping("filmEdit/duration/{id}")
    public String filmEditDurationPost(@PathVariable(value = "id") long filmId,
                                       @RequestParam(value = "updatedHours") int hours,
                                       @RequestParam(value = "updatedMinutes") int minutes,
                                       @RequestParam(value = "updatedSeconds") int seconds, Model model) {
        String result = "editFilmDuration";


        if (model.containsAttribute("exception")) {
            model.addAttribute("exception", null);
        }
        if (filmId > 0 && hours > -1 && minutes > -1 && seconds > -1) {
            FilmDto filmDto = filmService.getFilmById(filmId);
            int updatedDuration = hours * 3600 + minutes * 60 + seconds;
            filmDto.setDuration(updatedDuration);

            boolean status = false;
            try {
                status = filmService.updateFilmDuration(filmDto);
                System.out.println(status + "status");
            } catch (Exception e) {
                model.addAttribute("exception", e.getMessage());
            }

            if (status) {
                result = "redirect:/filmEdit/" + filmId;
            } else {
                model.addAttribute("paramForUpdate", "Duration");
                model.addAttribute("valueForUpdate", filmDto.getDuration());
            }
        } else {
            model.addAttribute("paramForUpdate", "Duration");
            model.addAttribute("exception", "Bad params");
        }

        return result;
    }
/*
    @GetMapping("filmEdit/description/{id}")
    public String filmEditDescription(@PathVariable(value = "id") long filmId, Model model) {
        FilmDto filmDto = filmService.getFilmById(filmId);

        String filmDescription;

        try {
            filmDescription = filmService.uploadDescriptionFromFile(filmDto.getDescription());
        } catch (IOException e) {
            filmDescription = "file not found";
        }

        model.addAttribute("exception", null);
        model.addAttribute("paramForUpdate", "Description");
        model.addAttribute("valueForUpdate", filmDescription);
        return "editFilmDescription";
    }

 */
/*
    @PostMapping("filmEdit/description/{id}")
    public String filmEditDescriptionPost(@PathVariable(value = "id") long filmId, @RequestParam(value = "updatedDescription") String updatedDescription, Model model) {
        String result = "editFilmDescription";

        if (model.containsAttribute("exception")) {
            model.addAttribute("exception", null);
        }
        if (filmId > 0 && updatedDescription != null) {
            FilmDto filmDto = filmService.getFilmById(filmId);


            boolean status = false;
            try {
                status = filmService.downloadDescriptionToFile(filmDto.getDescription(), updatedDescription);
            } catch (Exception e) {
                model.addAttribute("exception", e.getMessage());
            }

            if (status) {
                result = "redirect:/filmEdit/" + filmId;
            } else {
                model.addAttribute("paramForUpdate", "Description");
                model.addAttribute("valueForUpdate", updatedDescription);
            }
        } else {
            model.addAttribute("paramForUpdate", "Description");
            model.addAttribute("exception", "Bad params");
        }

        return result;
    }
 */

    @GetMapping("filmEdit/ageLimit/{id}")
    public String filmEditAgeLimit(@PathVariable(value = "id") long filmId, Model model) {
        model.addAttribute("exception", null);
        model.addAttribute("paramForUpdate", "AgeLimit");
        model.addAttribute("valueForUpdate", AgeLimit.values());
        return "editFilmAgeLimit";
    }


}
