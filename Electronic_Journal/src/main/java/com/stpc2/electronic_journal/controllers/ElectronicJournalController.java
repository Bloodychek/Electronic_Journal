package com.stpc2.electronic_journal.controllers;

import com.stpc2.electronic_journal.models.ElectronicJournal;
import com.stpc2.electronic_journal.repositories.ElectronicJournalRepo;
import com.stpc2.electronic_journal.serviceImpl.UserService;
import com.stpc2.electronic_journal.services.ElectronicJournalService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ElectronicJournalController {
    private final ElectronicJournalService electronicJournalService;
    private final ElectronicJournalRepo electronicJournalRepo;

    @GetMapping("/electronicJournal/index")
    public String index(Model model) {
        model.addAttribute("electronicJournal", electronicJournalService.findAll());
        return findPaginated(1, "eventTime", "desc", model);
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

    @GetMapping("/electronicJournal/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir, Model model) {
        int pageSize = 10;

        Page<ElectronicJournal> page = electronicJournalService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<ElectronicJournal> electronicJournalList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("desc") ? "asc" : "desc");

        model.addAttribute("electronicJournal", electronicJournalList);
        return "electronicJournal/index";
    }

    @GetMapping("/electronicJournal/date")
    public String findAllByEventTimeBetween(@RequestParam(name = "eventTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventTime,
                                            @RequestParam(name = "recordCreationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime recordCreationDate, Model model) {
            if(eventTime != null && recordCreationDate != null){
                List<ElectronicJournal> findByRangeDate = electronicJournalRepo.findAllByEventTimeBetween(eventTime, recordCreationDate);
                model.addAttribute("electronicJournal", findByRangeDate);
                return "electronicJournal/index";
            }
            else {
                return "electronicJournal/index";
            }
            //return findPaginated(1,"eventTime", "asc", model);

    }
}