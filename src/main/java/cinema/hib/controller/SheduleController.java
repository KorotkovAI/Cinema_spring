package cinema.hib.controller;

import cinema.hib.dto.model.HallDto;
import cinema.hib.service.impl.HallServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("shedule")
public class SheduleController {

    @Autowired
    HallServiceImpl hallService;

    @GetMapping("/halls")
    public String hallsInShedule(Model model) {
        List<HallDto> hallDtos = hallService.getAll();
        model.addAttribute("halls", hallDtos);
        return "sheduleHalls";
    }

    @GetMapping("edit/{id}")
    public String pickDate(@PathVariable(value = "id") int hallId, Model model) {

        return "shedulePickDate";
    }

    @PostMapping("edit/{id}")
    public String confirmDate(@PathVariable(value = "id") int hallId, Model model, HttpServletRequest request) {
        System.out.println(hallId);
        System.out.println(request.getParameter("InputDate"));
        return "films";
    }
}
