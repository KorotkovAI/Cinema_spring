package cinema.hib.controller;


import cinema.hib.dto.model.FilmDto;
import cinema.hib.model.Film;
import cinema.hib.service.impl.FilmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FilmListController {

    @Autowired
    FilmServiceImpl filmService;

    @GetMapping("films")
    public String films(Model model) {
        return findPaginated(1, "id", "asc", model);
    }

    @GetMapping("films/{pageNo}")
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


}
