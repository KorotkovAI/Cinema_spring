package cinema.hib.controller;

import cinema.hib.dto.model.FilmDto;
import cinema.hib.dto.model.FilmDtoShort;
import cinema.hib.dto.model.GenreDto;
import cinema.hib.model.AgeLimit;
import cinema.hib.service.impl.FilmServiceImpl;
import cinema.hib.service.impl.GenreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        Page<FilmDto> page = filmService.findPaginated(pageNo, pageSize, sortField, sortDir);


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

        if (!model.containsAttribute("exception")) {
            model.addAttribute("exception", null);
        }

        List<GenreDto> genreDtos = genreService.getAll();
        model.addAttribute("film", new FilmDtoShort());
        model.addAttribute("limits", AgeLimit.values());
        model.addAttribute("genres", genreDtos);

        return "addFilm";
    }

    @PostMapping("/add")
    public String saveNewFilm(@Valid @ModelAttribute("film") FilmDtoShort dto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        String result = "redirect:/films/all";

        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            List<String> exceptions = errors.stream().map(ent -> ent.getDefaultMessage()).collect(Collectors.toList());
            redirectAttributes.addFlashAttribute("exception", exceptions);
            result = "redirect:/films/add";
        } else {
            filmService.saveDtoShortToFilm(dto);
        }

        return result;
    }


    @GetMapping("edit/{id}")
    public String editFilm(@PathVariable(value = "id") long filmId, Model model) {
        FilmDto filmDto = filmService.getFilmById(filmId);
        model.addAttribute("film", filmDto);
        model.addAttribute("limits", AgeLimit.values());
        model.addAttribute("genres", genreService.getAll());
        if (!model.containsAttribute("exception")) {
            model.addAttribute("exception", null);
        }

        return "editFilm";
    }

    @PostMapping("edit/{id}")
    public String saveEditedFilm(@PathVariable(value = "id") int filmId, @Valid @ModelAttribute("film") FilmDto dto,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        String result = "redirect:/films/all";

        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            List<String> exceptions = errors.stream().map(ent -> ent.getDefaultMessage()).collect(Collectors.toList());
            redirectAttributes.addFlashAttribute("exception", exceptions);
            result = "redirect:/films/edit/" + filmId;
        } else {
            filmService.saveDtoToFilm(dto);
        }

        return result;
    }

}
