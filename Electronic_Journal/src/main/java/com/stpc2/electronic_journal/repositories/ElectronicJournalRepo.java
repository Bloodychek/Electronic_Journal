package com.stpc2.electronic_journal.repositories;

import com.stpc2.electronic_journal.models.ElectronicJournal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ElectronicJournalRepo extends JpaRepository<ElectronicJournal, Integer> {
    List<ElectronicJournal> findAllByEventTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    @Query(value = "select calc_brigada_20(sysdate) from dual", nativeQuery = true)
    Integer getBrigadeNumber();
}