package com.stpc2.electronic_journal.serviceImpl;

import com.stpc2.electronic_journal.models.ElectronicJournal;
import com.stpc2.electronic_journal.repositories.ElectronicJournalRepo;
import com.stpc2.electronic_journal.services.ElectronicJournalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ElectronicJournalServiceImpl implements ElectronicJournalService {
    private final ElectronicJournalRepo electronicJournalRepo;

    @Override
    public List<ElectronicJournal> findAll() {
        return electronicJournalRepo.findAll();
    }

    @Override
    public ElectronicJournal getElectronicJournalById(int id) {
        Optional<ElectronicJournal> optional = electronicJournalRepo.findById(id);
        ElectronicJournal electronicJournal = null;
        if (optional.isPresent()) {
            electronicJournal = optional.get();
        } else {
            throw new RuntimeException("Не найдено записи по id: " + id);
        }
        return electronicJournal;
    }

    @Override
    public void save(ElectronicJournal electronicJournal) {
        electronicJournalRepo.saveAndFlush(electronicJournal);
    }

    @Override
    public void delete(int id) {
        this.electronicJournalRepo.deleteById(id);
    }

    @Override
    public Page<ElectronicJournal> findPaginated(int pageNumber, String sortField, String sortDir, LocalDateTime eventTime, LocalDateTime recordCreationDate) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortField).descending() :
                Sort.by(sortField).ascending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 10, sort);
        if(eventTime != null && recordCreationDate != null){
            return electronicJournalRepo.findAllByEventTimeBetween(eventTime, recordCreationDate, pageable);
        }
        return this.electronicJournalRepo.findAll(pageable);
    }

    @Override
    public List<ElectronicJournal> findAllByEventDescription(String eventDescription){
        if(eventDescription != null){
            return electronicJournalRepo.findAllByEventDescription(eventDescription);
        }
        return electronicJournalRepo.findAll();
    }
}
