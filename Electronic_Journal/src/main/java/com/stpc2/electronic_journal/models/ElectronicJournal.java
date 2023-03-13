package com.stpc2.electronic_journal.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ELECTRONIC_JOURNAL", schema = "mk_plus")
public class ElectronicJournal {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ELECTRONIC_JOURNAL_ID")
    @SequenceGenerator(name = "SEQ_ELECTRONIC_JOURNAL_ID", sequenceName = "SEQ_ELECTRONIC_JOURNAL_ID", allocationSize = 1)
    private Integer id;

    @Column(name = "OFFICER_FULL_NAME")
    private String officerFullName;

    @Column(name = "BRIGADE_NUMBER")
    private Integer brigadeNumber;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "EVENT_TIME")
    private LocalDateTime eventTime;

    @Column(name = "EVENT_DESCRIPTION")
    private String eventDescription;

    @Column(name = "DUTY_OFFICER")
    private String dutyOfficer;

    @Column(name = "ACTION_STATUS")
    private String actionStatus;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "RECORD_CREATION_DATE")
    private LocalDateTime recordCreationDate;
}

