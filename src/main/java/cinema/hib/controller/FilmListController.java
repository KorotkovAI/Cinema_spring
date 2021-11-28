package cinema.hib.controller;

import cinema.hib.model.Film;
import cinema.hib.service.impl.FilmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FilmListController {

    @Autowired
    FilmServiceImpl filmService;

    @GetMapping("films")
    public String films(Model model) {
        List<Film> filmList = filmService.getAll();
        filmList.stream().forEach(System.out::println);
        model.addAttribute("filmsList", filmList);
        return "films";
    }


}
