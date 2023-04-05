package com.stpc2.electronic_journal.services;

import com.stpc2.electronic_journal.models.ElectronicJournal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;

public interface ElectronicJournalService {
    List<ElectronicJournal> findAll();

    ElectronicJournal getElectronicJournalById(int id);

    void save(ElectronicJournal electronicJournal);

    void delete(int id);

    Page<ElectronicJournal> findPaginated(int pageNumber, String sortField, String sortDir, LocalDateTime eventTime, LocalDateTime recordCreationDate);

    List<ElectronicJournal> findAllByEventDescription(String eventDescription);
}
