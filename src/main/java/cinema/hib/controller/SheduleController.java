package cinema.hib.controller;

import cinema.hib.dto.model.FilmDto;
import cinema.hib.dto.model.HallDto;
import cinema.hib.dto.model.SheduleDto;
import cinema.hib.dto.model.SlotDto;
import cinema.hib.service.impl.FilmServiceImpl;
import cinema.hib.service.impl.HallServiceImpl;
import cinema.hib.service.impl.SheduleServiceImpl;
import cinema.hib.service.impl.SlotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Controller
@RequestMapping("shedule")
public class SheduleController {

    @Autowired
    HallServiceImpl hallService;

    @Autowired
    SheduleServiceImpl sheduleService;

    @Autowired
    FilmServiceImpl filmService;

    @Autowired
    SlotServiceImpl slotService;

    @GetMapping("/halls")
    public String hallsInShedule(Model model) {
        List<HallDto> hallDtos = hallService.getAll();
        model.addAttribute("halls", hallDtos);
        return "sheduleHalls";
    }

    @GetMapping("edit/{id}")
    public String pickDate(@PathVariable(value = "id") int hallId, Model model) {
        if (!model.containsAttribute("exception")) {
            model.addAttribute("exception", null);
        }
        model.addAttribute("hallName", hallService.getHallById(hallId).getName());
        return "shedulePickDate";
    }

    @PostMapping("edit/{id}")
    public String confirmDate(@PathVariable(value = "id") int hallId, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String result = "redirect:/shedule/edit/slots/" + hallId;

        try {
            LocalDate localDate = LocalDate.parse(request.getParameter("InputDate"));
            if (localDate.isBefore(LocalDate.now())) {
                redirectAttributes.addFlashAttribute("exception", "You can`t edit dates before today");
                result = "redirect:/shedule/edit/" + hallId;
            } else {
                model.addAttribute("date", localDate);
            }
        }catch (DateTimeParseException e) {
            redirectAttributes.addFlashAttribute("exception", e.getMessage());
            result = "redirect:/shedule/edit/" + hallId;
        }
        return result;
    }

    @GetMapping("edit/slots/{id}")
    public String sheduleSlots(@PathVariable(value = "id") int hallId, Model model){
        LocalDate date = null;
        SheduleDto sheduleDto;
        try {
            date = (LocalDate) model.getAttribute("date");
        } catch (Exception e) {
            model.addAttribute("exception", "Some problems with date");
        }

        HallDto hallDto = hallService.getHallById(hallId);
        try {
            sheduleDto = sheduleService.getSheduleByHall(hallDto);
        } catch (NullPointerException e) {
         model.addAttribute("exception", "For this date and hall we haven`t shedule");
         sheduleDto = new SheduleDto();
         sheduleDto.setHallDto(hallDto);
        }

        List<SlotDto> slotDtos= sheduleService.getSlotsCurrentDate(sheduleDto, date);

        model.addAttribute("hall", hallDto);
        model.addAttribute("slots", slotDtos);
        model.addAttribute("date", date);
        return "slots";
    }

    @GetMapping("edit/slots/add/{id}")
    public String addSlot(@PathVariable(value = "id") int hallId, Model model) {
        if (!model.containsAttribute("exception")) {
            model.addAttribute("exception", null);
        }
        model.addAttribute("slot", new SlotDto());
        model.addAttribute("hall", hallService.getHallById(hallId));
        model.addAttribute("films", filmService.findAll());
        return "addSlot";
    }

    @PostMapping("edit/slots/add/{id}")
    public String saveNewFilm(@PathVariable(value = "id") int hallId, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String result = "redirect:/edit/slots/" + hallId;

        LocalDate date = LocalDate.parse(request.getParameter("dateOfFilm"));
        LocalTime startTime = LocalTime.parse(request.getParameter("startTime"));
        LocalTime endTime = LocalTime.parse(request.getParameter("endTime"));
        long filmId = Integer.parseInt(request.getParameter("film"));

        SlotDto slotDto = new SlotDto();
        slotDto.setFilm(filmService.getFilmById(filmId));
        slotDto.setStartTime(startTime);
        slotDto.setEndTime(endTime);
        slotDto.setDateOfFilm(date);
        System.out.println(slotDto);

        try {
            System.out.println("111");
            SlotDto resultSlot = slotService.saveSlot(slotDto);
            System.out.println("222");
            System.out.println(resultSlot);
            boolean resultUpdating = false;
            resultUpdating = sheduleService.updateShedule(hallService.getHallById(hallId), resultSlot);
            System.out.println(resultUpdating);
            if (!resultUpdating) {
                boolean deleteSlot = slotService.deleteSlot(resultSlot);
                System.out.println(deleteSlot);
            }
        } catch (javax.validation.ConstraintViolationException e) {
            ConstraintViolation<?> problem = e.getConstraintViolations().stream().findFirst().get();
            String exception = problem.getPropertyPath().toString() + " " + e.getConstraintViolations().stream().findFirst().get().getMessage();
            redirectAttributes.addFlashAttribute("exception", exception);
            result = "redirect:/edit/slots/add/" + hallId;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("exception", e.getMessage());
            result = "redirect:/edit/slots/add/" + hallId;
        }

        return result;
    }


}
