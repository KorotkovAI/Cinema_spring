package cinema.hib.controller;

import cinema.hib.dto.model.*;
import cinema.hib.model.SeatType;
import cinema.hib.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    FilmPriceServiceImpl filmPriceService;

    @GetMapping("/halls")
    public String hallsInShedule(Model model) {
        List<HallDto> hallDtos = hallService.getAll();
        model.addAttribute("halls", hallDtos);
        return "sheduleHalls";
    }

    @GetMapping("edit/{idHall}")
    public String pickDate(@PathVariable(value = "idHall") int hallId, Model model) {

        if (!model.containsAttribute("exception")) {
            model.addAttribute("exception", null);
        }

        model.addAttribute("hallName", hallService.getHallById(hallId).getName());
        return "shedulePickDate";
    }

    @PostMapping("edit/{idHall}")
    public String confirmDate(@PathVariable(value = "idHall") int hallId, Model model,
                              @RequestParam("InputDate") String date, RedirectAttributes redirectAttributes) {
        String result = "redirect:/shedule/slots/" + hallId;

        LocalDate localDate;

        try {
            localDate = LocalDate.parse(date);

            if (localDate != null && localDate.isBefore(LocalDate.now())) {
                redirectAttributes.addFlashAttribute("exception", "You can`t edit dates before today");
                result = "redirect:/shedule/edit/" + hallId;
            } else {
                String dateToPath = null;
                if (localDate != null) {
                    dateToPath = localDate.toString();
                }
                result = result + "/" + dateToPath;
            }
        } catch (DateTimeParseException e) {
            redirectAttributes.addFlashAttribute("exception", e.getMessage());
            result = "redirect:/shedule/edit/" + hallId;
        }

        return result;
    }

    @GetMapping("slots/{idHall}/{date}")
    public String sheduleSlots(@PathVariable(value = "idHall") int hallId, @PathVariable(value = "date") String date,
                               Model model, RedirectAttributes redirectAttributes) {
        String result = "slots";
        System.out.println("----------------");
        LocalDate localDate = null;

        try {
            localDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            redirectAttributes.addFlashAttribute("exception", e.getMessage());
            result = "redirect:/slots/" + hallId;
        }

        SheduleDto sheduleDto;
        HallDto hallDto = hallService.getHallById(hallId);
        System.out.println("halldto!!! " + hallDto);
        try {
            sheduleDto = sheduleService.getSheduleByHall(hallDto);
            System.out.println("sheduledto!!! " + sheduleDto);
        } catch (NullPointerException e) {
            System.out.println("1111111111");
            model.addAttribute("exception", "For this date and hall we haven`t shedule");
            sheduleDto = new SheduleDto();
            sheduleDto.setHallDto(hallDto);
        }

        List<SlotDto> slotDtos = sheduleService.getSlotsCurrentDate(sheduleDto, localDate);
        System.out.println("list of slots dtos" + slotDtos);
        model.addAttribute("hall", hallDto);
        model.addAttribute("slots", slotDtos);
        model.addAttribute("date", localDate);
        return result;
    }

    @GetMapping("slots/{idHall}/add/{date}")
    public String addSlot(@PathVariable(value = "idHall") int hallId, @PathVariable(value = "date") String date,
                          Model model) {

        if (!model.containsAttribute("exception")) {
            model.addAttribute("exception", null);
        }

        model.addAttribute("slot", new SlotDtoShort());
        model.addAttribute("hallName", hallService.getHallById(hallId).getName());
        model.addAttribute("films", filmService.findAll());

        return "addSlot";
    }

    @PostMapping("slots/{idHall}/add/{date}")
    public String saveNewFilm(@PathVariable(value = "idHall") int hallId, @PathVariable(value = "date") String date,
                              @Valid @ModelAttribute("slot") SlotDtoShort dto,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        String result;
        try {
            LocalDate localDate = LocalDate.parse(dto.getDateOfFilm());

            if (localDate != null && localDate.isBefore(LocalDate.now())) {
                redirectAttributes.addFlashAttribute("exception", "You can`t add film to this day");
                result = "redirect:/shedule/slots/" + hallId + "/add/" + dto.getDateOfFilm();
            } else {
                try {
                    SlotDto resultSlot = slotService.saveDtoShortToSlot(dto);
                    boolean resultUpdating;
                    resultUpdating = sheduleService.updateShedule(hallService.getHallById(hallId), resultSlot);
                    System.out.println("sheduleservice after update " + sheduleService.getSheduleByHall(hallService.getHallById(hallId)));
                    System.out.println("resultUpdating " + resultUpdating);
                    if (!resultUpdating) {
                        slotService.deleteSlot(resultSlot);
                        redirectAttributes.addFlashAttribute("exception", "Can`t save this slot? because " +
                                "in shedule have one with such parametrs");
                        result = "redirect:/shedule/slots/" + hallId + "/add/" + dto.getDateOfFilm();
                    } else {
                        result = "redirect:/shedule/slots/" + hallId + "/" + dto.getDateOfFilm();
                    }
                } catch (NumberFormatException e) {
                    redirectAttributes.addFlashAttribute("exception", e.getMessage());
                    result = "redirect:/shedule/slots/" + hallId + "/add/" + dto.getDateOfFilm();
                }

            }
        } catch (DateTimeParseException e) {
            redirectAttributes.addFlashAttribute("exception", e.getMessage());
            result = "redirect:/shedule/slots/" + hallId + "/add/" + dto.getDateOfFilm();
        }


        return result;
    }

    @GetMapping("slot/editPrice/{id}")
    public String editPrice(@PathVariable(value = "id") long slotId, Model model) {
        if (!model.containsAttribute("exception")) {
            model.addAttribute("exception", null);
        }

        SlotDto slotDto = slotService.getSlotById(slotId);
        SheduleDto sheduleDto = sheduleService.getSheduleBySlot(slotDto);
        List<FilmPriceDto> filmPriceDtoList = filmPriceService.getBySlot(slotDto);
//TODO can`t get seatType from film
        //List<SeatDto> seatTypes = filmPriceDtoList.stream().map(ent -> ent.getSeat()).collect(Collectors.toList());
        model.addAttribute("hall", sheduleDto.getHallDto());
        model.addAttribute("slot", slotDto);
        model.addAttribute("typeSeats", SeatType.values());
        return "editPriceSlot";
    }


}
