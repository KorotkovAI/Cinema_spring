package cinema.hib.controller;

import cinema.hib.dto.model.FilmDto;
import cinema.hib.dto.model.FilmDtoShort;
import cinema.hib.dto.model.FilmDtoToPage;
import cinema.hib.dto.model.GenreDto;
import cinema.hib.model.AgeLimit;
import cinema.hib.service.impl.FilmServiceImpl;
import cinema.hib.service.impl.GenreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("films")
public class FilmController {

    @Autowired
    FilmServiceImpl filmService;

    @Autowired
    GenreServiceImpl genreService;

    @GetMapping("/all")
    public String films(Model model) {
        return findPaginated(1, "id", "asc", model);
    }

    @GetMapping("/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir, Model model) {
        int pageSize = 5;

        Page<FilmDtoToPage> page = filmService.findPaginated(pageNo, pageSize, sortField, sortDir);


        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listFilms", page.getContent());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        return "films";
    }

    @GetMapping("/add")
    public String createFilm(Model model) {

        model.addAttribute("film", new FilmDtoShort());
        model.addAttribute("limits", AgeLimit.values());

        return "addFilm";
    }

    @PostMapping("/add")
    public String saveNewFilm(@Valid @ModelAttribute("film") FilmDtoShort dto, BindingResult bindingResult, Model model) {
        String result;

        if (bindingResult.hasErrors()) {
            model.addAttribute("limits", AgeLimit.values());
            result = "addFilm";
        } else {
            FilmDto filmDto = filmService.saveDtoShortToFilm(dto);
            result = "redirect:/films/edit/editGenres/" + filmDto.getId();
        }

        return result;
    }

    @GetMapping("edit/editGenres/{filmId}")
    public String editGenres(@PathVariable(value = "filmId") long filmId, Model model) {
        FilmDto filmDto = filmService.getFilmById(filmId);

        if (!model.containsAttribute("exception")) {
            model.addAttribute("exception", null);
        }

        model.addAttribute("genres", filmDto.getGenres());
        model.addAttribute("filmId", filmDto.getId());

        return "editGenres";
    }

    @PostMapping("edit/editGenres/{filmId}")
    public String saveGenres(@PathVariable(value = "filmId") long filmId,
                             RedirectAttributes redirectAttributes) {
        String result;
        FilmDto filmDto = filmService.getFilmById(filmId);

        if (filmDto.getGenres().size() > 0) {
            result = "redirect:/films/all";
        } else {
            redirectAttributes.addFlashAttribute("exception",
                    "You can`t save film without genres");
            result = "redirect:/films/edit/editGenres/" + filmDto.getId();
        }
        return result;
    }

    @GetMapping("edit/editGenres/addGenre/{filmId}")
    public String chooseGenre(Model model) {

        if (!model.containsAttribute("exception")) {
            model.addAttribute("exception", null);
        }

        model.addAttribute("allGenres", genreService.getAll());

        return "addGenre";
    }

    @PostMapping("edit/editGenres/addGenre/{filmId}")
    public String addGenre(@PathVariable(value = "filmId") long filmId,
                           @RequestParam("InputGenre") int genreId, Model model, RedirectAttributes redirectAttributes) {
        String result;
        FilmDto filmDto = filmService.getFilmById(filmId);

        try {
            GenreDto dto = genreService.dtoReadById(genreId);
            if (!filmDto.getGenres().contains(dto)) {
                filmDto.getGenres().add(dto);
                filmService.saveDtoToFilm(filmDto);
                result = "redirect:/films/edit/editGenres/" + filmDto.getId();
            } else {
                redirectAttributes.addFlashAttribute("exception",
                        "You can`t add this genre, because this film already contain it");
                result = "redirect:/films/edit/editGenres/addGenre/" + filmDto.getId();
            }
        } catch (EntityNotFoundException e) {
            model.addAttribute("allGenres", genreService.getAll());
            result = "addGenre";
        }
        return result;
    }

    @GetMapping("edit/editGenres/genreDelete/{film}/{id}")
    public String deleteGenre(@PathVariable(value = "film") long filmId,
                              @PathVariable(value = "id") int genreId, RedirectAttributes redirectAttributes) {

        FilmDto filmDto = filmService.getFilmById(filmId);
        GenreDto genreDto = genreService.dtoReadById(genreId);

        if (filmDto.getGenres().contains(genreDto)) {
            filmDto.getGenres().remove(genreDto);
            filmService.saveDtoToFilm(filmDto);
        } else {
            redirectAttributes.addFlashAttribute("exception",
                    "Sorry some problems with type of genre");
        }
        return "redirect:/films/edit/editGenres/" + filmDto.getId();
    }


    @GetMapping("edit/{id}")
    public String editFilm(@PathVariable(value = "id") long filmId, Model model) {
        List<String> genreNames = null;

        if (filmService.getFilmById(filmId).getGenres() != null) {
            genreNames = filmService.getFilmById(filmId).getGenres().stream().
                    map(GenreDto::getName).collect(Collectors.toList());
        }

        model.addAttribute("film", filmService.getFilmById(filmId));
        model.addAttribute("limits", AgeLimit.values());
        model.addAttribute("filmGenres", genreNames);

        return "editFilm";
    }

    @PostMapping("edit/{id}")
    public String saveEditedFilm(@PathVariable(value = "id") long filmId, @Valid @ModelAttribute("film") FilmDto dto,
                                 BindingResult bindingResult, Model model) {
        String result = "redirect:/films/all";

        if (bindingResult.hasErrors()) {
            model.addAttribute("limits", AgeLimit.values());
            result = "redirect:/films/edit/" + filmId;
        } else {
            filmService.saveDtoToFilm(dto);
        }

        return result;
    }

}
