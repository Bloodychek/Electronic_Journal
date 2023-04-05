package com.stpc2.electronic_journal.controllers;

import com.stpc2.electronic_journal.models.ElectronicJournal;
import com.stpc2.electronic_journal.repositories.ElectronicJournalRepo;
import com.stpc2.electronic_journal.services.ElectronicJournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ElectronicJournalController {
    private final ElectronicJournalService electronicJournalService;
    private final ElectronicJournalRepo electronicJournalRepo;

    @GetMapping("/electronicJournal/index")
    public String index(Model model) {
        model.addAttribute("electronicJournal", electronicJournalService.findAll());
        return findPaginated(model, 1, "eventTime", "desc", null, null);
    }

    @GetMapping("/electronicJournal/create")
    public String create(Model model) {
        ElectronicJournal electronicJournal = new ElectronicJournal();
        model.addAttribute("electronicJournal", electronicJournal);
        return "electronicJournal/create";
    }

    @PostMapping("/electronicJournal/save")
    public String save(Principal principal, @ModelAttribute("electronicJournal") @Valid ElectronicJournal electronicJournal) {
        electronicJournal.setOfficerFullName(principal.getName());
        electronicJournal.setBrigadeNumber(electronicJournalRepo.getBrigadeNumber());
        electronicJournal.setRecordCreationDate(LocalDateTime.now());
        electronicJournalService.save(electronicJournal);
        return "redirect:/electronicJournal/index";
    }

    @GetMapping("/electronicJournal/edit/{id}")
    public String edit(@PathVariable(value = "id") int id, Model model) {
        ElectronicJournal electronicJournal = electronicJournalService.getElectronicJournalById(id);
        electronicJournal.setRecordCreationDate(LocalDateTime.now());
        model.addAttribute("electronicJournal", electronicJournal);
        return "electronicJournal/edit";
    }

//    @PostMapping("/electronicJournal/index")
//    //Сохранение внесенных изменений
//    public String stageEditSave(
//            @RequestParam Integer id,
//            @RequestParam String officerFullName,
//            @RequestParam int brigadeNumber,
//            @RequestParam LocalDateTime eventTime,
//            @RequestParam String eventDescription,
//            @RequestParam String dutyOfficer,
//            @RequestParam String actionStatus,
//            @RequestParam LocalDateTime recordCreationDate,
//            @Valid ElectronicJournal electronicJournal
//    ) {
//        electronicJournal.setId(id);
//        electronicJournal.setOfficerFullName(officerFullName);
//        electronicJournal.setBrigadeNumber(brigadeNumber);
//        electronicJournal.setEventTime(eventTime);
//        electronicJournal.setEventDescription(eventDescription);
//        electronicJournal.setDutyOfficer(dutyOfficer);
//        electronicJournal.setActionStatus(actionStatus);
//        electronicJournal.setRecordCreationDate(recordCreationDate);
//        electronicJournalService.save(electronicJournal);
//
//        return "redirect:/electronicJournal/index";
//    }

    @GetMapping("/electronicJournal/delete/{id}")
    public String delete(@PathVariable(value = "id") int id) {
        this.electronicJournalService.delete(id);
        return "redirect:/electronicJournal/index";
    }

    @GetMapping("/electronicJournal/page/{pageNumber}")
    public String findPaginated(Model model, @PathVariable(value = "pageNumber") int currentPage, @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir, @RequestParam(name = "eventTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @Nullable LocalDateTime eventTime,
                                @RequestParam(name = "recordCreationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @Nullable LocalDateTime recordCreationDate) {

        Page<ElectronicJournal> page = electronicJournalService.findPaginated(currentPage, sortField, sortDir, eventTime, recordCreationDate);
        List<ElectronicJournal> electronicJournalList = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("electronicJournal", electronicJournalList);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("eventTime", eventTime);
        model.addAttribute("recordCreationDate", recordCreationDate);
        model.addAttribute("reverseSortDir", sortDir.equals("desc") ? "asc" : "desc");

        return "electronicJournal/index";
    }

    @GetMapping("/electronicJournal/description")
    public String findAllByEventDescription(@RequestParam(name = "eventDescription") @Nullable String eventDescription, Model model) {
        model.addAttribute("electronicJournal", electronicJournalService.findAllByEventDescription(eventDescription));
        return "electronicJournal/index";
    }
}