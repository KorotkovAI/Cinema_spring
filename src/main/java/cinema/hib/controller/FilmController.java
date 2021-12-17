package cinema.hib.controller;

import cinema.hib.dto.model.FilmDto;
import cinema.hib.model.AgeLimit;
import cinema.hib.service.impl.FilmServiceImpl;
import cinema.hib.service.impl.GenreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

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
        model.addAttribute("film", new FilmDto());
        model.addAttribute("limits", AgeLimit.values());

        return "addFilm";
    }

    @PostMapping("/add")
    public String saveNewFilm(FilmDto dto, RedirectAttributes redirectAttributes) {
        String result = "redirect:/films/all";

        try {
            filmService.saveFilm(dto);
        } catch (javax.validation.ConstraintViolationException e) {
            ConstraintViolation<?> problem = e.getConstraintViolations().stream().findFirst().get();
            String exception = problem.getPropertyPath().toString() + " " + e.getConstraintViolations().stream().findFirst().get().getMessage();
            System.out.println(problem.getPropertyPath().toString());
            redirectAttributes.addFlashAttribute("exception", exception);
            result = "redirect:/films/add";
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
    public String saveEditedFilm(@PathVariable(value = "id") int filmId, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        String result = "redirect:/films/all";
        System.out.println(request.getParameter("name"));
        System.out.println(request.getParameter("genreUsed"));
        System.out.println(request.getParameter("duration"));
        System.out.println(request.getParameter("ageLimit"));
        System.out.println(request.getParameter("description"));
        System.out.println(request.getParameter("genre"));
        System.out.println(request.getParameter("genres"));
        try {
           // filmService.saveFilm(dto);
        } catch (javax.validation.ConstraintViolationException e) {
            ConstraintViolation<?> problem = e.getConstraintViolations().stream().findFirst().get();
            String exception = problem.getPropertyPath().toString() + " " + e.getConstraintViolations().stream().findFirst().get().getMessage();
            System.out.println(problem.getPropertyPath().toString());
            redirectAttributes.addFlashAttribute("exception", exception);
            result = "redirect:/films/edit/" + filmId;
        }

        return result;
    }

}
