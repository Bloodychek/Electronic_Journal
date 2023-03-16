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
            throw new RuntimeException("Electronic journal not found by id: " + id);
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
    public Page<ElectronicJournal> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortField).descending() :
                Sort.by(sortField).ascending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.electronicJournalRepo.findAll(pageable);
    }
}
