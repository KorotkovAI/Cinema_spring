package cinema.hib.controller;

import cinema.hib.dto.model.FilmDto;
import cinema.hib.model.AgeLimit;
import cinema.hib.service.impl.FilmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.ConstraintViolation;
import java.util.Arrays;

@Controller
@RequestMapping("films")
public class FilmController {

    @Autowired
    FilmServiceImpl filmService;

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
    public String saveFilm(FilmDto dto, RedirectAttributes redirectAttributes) {
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


}
