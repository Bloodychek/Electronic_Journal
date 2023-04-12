package com.stpc2.electronic_journal.repositories;

import com.stpc2.electronic_journal.beans.Constants;
import com.stpc2.electronic_journal.models.ElectronicJournal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ElectronicJournalRepo extends JpaRepository<ElectronicJournal, Integer> {
    /**
     * Method contains sql query that searches for data by the eventDescription field between two dates
     * @param eventTime - start date
     * @param recordCreationDate - end date
     * @param eventDescription - problem description
     * @param pageable - Pageable class object
     * @return
     */
    @Query(value = Constants.QUERY_MULTIPLE_SEARCH)
    Page<ElectronicJournal> findAllByEventTimeBetweenAndRecordCreationDate(LocalDateTime eventTime, LocalDateTime recordCreationDate, String eventDescription, Pageable pageable);

    /**
     * Method contains sql query that calculate the number of the brigade
     * @return
     */
    @Query(value = Constants.QUERY_BRIGADE_NUMBER, nativeQuery = true)
    Integer getBrigadeNumber();
}