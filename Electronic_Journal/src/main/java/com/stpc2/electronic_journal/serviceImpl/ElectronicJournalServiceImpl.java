package com.stpc2.electronic_journal.serviceImpl;

import com.stpc2.electronic_journal.beans.Constants;
import com.stpc2.electronic_journal.models.ElectronicJournal;
import com.stpc2.electronic_journal.repositories.ElectronicJournalRepo;
import com.stpc2.electronic_journal.services.ElectronicJournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * A class that implements business logic and contains methods of the ElectronicJournalService interface
 */
@Service
@RequiredArgsConstructor
public class ElectronicJournalServiceImpl implements ElectronicJournalService {
    private final ElectronicJournalRepo electronicJournalRepo;

    /**
     * Method that displays all records
     * @return
     */
    @Override
    public List<ElectronicJournal> findAll() {
        return electronicJournalRepo.findAll();
    }

    /**
     * Method that get record by id
     * @param id - Field id
     * @return
     */
    @Override
    public ElectronicJournal getElectronicJournalById(int id) {
        Optional<ElectronicJournal> optional = electronicJournalRepo.findById(id);
        ElectronicJournal electronicJournal = null;
        if (optional.isPresent()) {
            electronicJournal = optional.get();
        } else {
            throw new RuntimeException(Constants.NOT_FOUNT_BY_ID + id);
        }
        return electronicJournal;
    }

    /**
     * Method that saves a record after edit and creating a new record
     * @param electronicJournal - Object of class ElectronicJournal
     */
    @Override
    public void save(ElectronicJournal electronicJournal) {
        electronicJournalRepo.saveAndFlush(electronicJournal);
    }

    /**
     * Method that deleting a specific record by id
     * @param id - Field id
     */
    @Override
    public void delete(int id) {
        this.electronicJournalRepo.deleteById(id);
    }

    /**
     * Method for pagination and data sorting
     * @param pageNumber - Page number
     * @param sortField - The field by which to sort
     * @param sortDir - Sort type
     * @param eventTime - Start date
     * @param recordCreationDate - End date
     * @param eventDescription - Description problem
     * @return
     */
    @Override
    public Page<ElectronicJournal> findPaginated(int pageNumber, String sortField, String sortDir, LocalDateTime eventTime, LocalDateTime recordCreationDate, String eventDescription) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortField).descending() :
                Sort.by(sortField).ascending();
        Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sort);
        if (eventTime != null && recordCreationDate != null && eventDescription != null) {
            return electronicJournalRepo.findAllByEventTimeBetweenAndRecordCreationDate(eventTime, recordCreationDate, eventDescription, pageable);
        }
        return this.electronicJournalRepo.findAll(pageable);
    }
}
