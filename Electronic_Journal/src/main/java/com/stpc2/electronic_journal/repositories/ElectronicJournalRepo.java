package com.stpc2.electronic_journal.repositories;

import com.stpc2.electronic_journal.models.ElectronicJournal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;

public interface ElectronicJournalRepo extends JpaRepository<ElectronicJournal, Integer> {
    Page<ElectronicJournal> findAllByEventTimeBetween(LocalDateTime eventTime, LocalDateTime recordCreationDate, Pageable pageable);

    @Query(value = "select * from ELECTRONIC_JOURNAL where EVENT_DESCRIPTION LIKE %?1%", nativeQuery = true)
    List<ElectronicJournal> findAllByEventDescription(String eventDescription);

    @Query(value = "select calc_brigada_20(sysdate) from dual", nativeQuery = true)
    Integer getBrigadeNumber();
}