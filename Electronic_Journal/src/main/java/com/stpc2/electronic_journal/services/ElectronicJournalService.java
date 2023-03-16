package com.stpc2.electronic_journal.services;

import com.stpc2.electronic_journal.models.ElectronicJournal;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface ElectronicJournalService {
    List<ElectronicJournal> findAll();

    ElectronicJournal getElectronicJournalById(int id);

    void save(ElectronicJournal electronicJournal);

    void delete(int id);

    Page<ElectronicJournal> findPaginated(int pageNo, int PageSize, String sortField, String sortDir);
}
