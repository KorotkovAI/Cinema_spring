package cinema.hib.controller;

import cinema.hib.dto.model.*;
import cinema.hib.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
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
    public String confirmDate(@PathVariable(value = "idHall") int hallId,
                              @RequestParam("InputDate") String date, RedirectAttributes redirectAttributes) {
        String result = "redirect:/shedule/edit/" + hallId;

        try {
            LocalDate currentDate = LocalDate.parse(date);

            if (currentDate.isBefore(LocalDate.now())) {
                redirectAttributes.addFlashAttribute("exception", "You can`t edit dates before today");
            } else {
                result = "redirect:/shedule/slots/" + hallId + "/" + currentDate;
            }
        } catch (DateTimeParseException e) {
            redirectAttributes.addFlashAttribute("exception", e.getMessage());
        }

        return result;
    }

    @GetMapping("slots/{idHall}/{date}")
    public String sheduleSlots(@PathVariable(value = "idHall") int hallId, @PathVariable(value = "date") String date,
                               Model model, RedirectAttributes redirectAttributes) {
        String result = "slots";

        try {
            LocalDate localDate = LocalDate.parse(date);

            HallDto hallDto = hallService.getHallById(hallId);

            List<SlotDto> slotDtos = sheduleService.getSlotsCurrentDate(hallDto, localDate);

            model.addAttribute("hall", hallDto);
            model.addAttribute("slots", slotDtos);
            model.addAttribute("date", localDate);
        } catch (DateTimeParseException e) {
            redirectAttributes.addFlashAttribute("exception", e.getMessage());
            result = "redirect:/edit/" + hallId;
        }
        return result;
    }

    @GetMapping("slots/{idHall}/add")
    public String addSlot(@PathVariable(value = "idHall") int hallId,
                          Model model) {

        if (!model.containsAttribute("exception")) {
            model.addAttribute("exception", null);
        }

        model.addAttribute("slot", new SlotDtoShort());
        model.addAttribute("hallName", hallService.getHallById(hallId).getName());
        model.addAttribute("films", filmService.findAll());

        return "addSlot";
    }

    @PostMapping("slots/{idHall}/add")
    public String saveNewFilm(@PathVariable(value = "idHall") int hallId,
                              @Valid @ModelAttribute("slot") SlotDtoShort dto,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        String result = "redirect:/shedule/slots/" + hallId + "/add";

        if (bindingResult.hasErrors()) {
            model.addAttribute("films", filmService.findAll());
        } else {
            try {
                LocalDate localDate = LocalDate.parse(dto.getDateOfFilm());

                if (localDate.isBefore(LocalDate.now())) {
                    redirectAttributes.addFlashAttribute("exception", "You can`t add film to this day");
                } else {
                    SlotDto resultSlot = slotService.saveDtoShortToSlot(dto);
                    boolean resultUpdating = sheduleService.updateShedule(hallService.getHallById(hallId).getName(), resultSlot.getId());

                    if (!resultUpdating) {
                        slotService.deleteSlot(resultSlot);
                        redirectAttributes.addFlashAttribute("exception", "Can`t save this slot? because " +
                                "in shedule have one with such parameters");
                    } else {
                        result = "redirect:/shedule/slots/" + hallId + "/" + dto.getDateOfFilm();
                    }
                }
            } catch (DateTimeParseException | NumberFormatException e) {
                redirectAttributes.addFlashAttribute("exception", e.getMessage());
            }
        }
        return result;
    }

    @GetMapping("slot/editPrice/{id}")
    public String editPrice(@PathVariable(value = "id") long slotId, Model model) {
        if (!model.containsAttribute("exception")) {
            model.addAttribute("exception", null);
        }
        /*
        SlotDto slotDto = slotService.getSlotById(slotId);
        SheduleDto sheduleDto = sheduleService.getSheduleBySlot(slotDto);
        List<FilmPriceDto> filmPriceDtoList = filmPriceService.getBySlot(slotDto);
//TODO can`t get seatType from film
        //List<SeatDto> seatTypes = filmPriceDtoList.stream().map(ent -> ent.getSeat()).collect(Collectors.toList());
        model.addAttribute("hall", sheduleDto.getHallDto());
        model.addAttribute("slot", slotDto);
        model.addAttribute("typeSeats", SeatType.values());
        return "editPriceSlot";

         */
        return null;
    }

    @GetMapping("slot/edit/{id}/{date}")
    public String editSlot(@PathVariable(value = "id") long slotId, Model model) {

        if (!model.containsAttribute("exception")) {
            model.addAttribute("exception", null);
        }

        model.addAttribute("slot", slotService.getSlotById(slotId));
        //TODO necessary to pick our film from list
        model.addAttribute("films", filmService.findAll());
        return "editSlot";
    }

    @PostMapping("slot/edit/{id}/{date}")
    public String saveEditedSlot(@PathVariable(value = "id") long slotId, @PathVariable(value = "date") String date,
                                 @Valid @ModelAttribute("slot") SlotDto dto, BindingResult bindingResult, Model model) {
        String result = "redirect:/slots/" + "";

        if (bindingResult.hasErrors()) {
            model.addAttribute("films", filmService.findAll());
            result = "redirect:/slot/edit/" + slotId + "/" + date;
        } else {
            slotService.saveSlot(dto);
        }
        return result;
    }

}
