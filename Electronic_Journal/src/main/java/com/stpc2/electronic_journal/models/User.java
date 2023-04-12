package com.stpc2.electronic_journal.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model class that stores user data from the database
 */
@Entity
@Data
@Table(name = "V_PERSONAL_STPC2", schema = "MK_PLUS")
public class User {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "SHORT_FIO")
    private String shortFIO;

    @Column(name = "PASSWORD")
    private String pass;

    @Column(name = "CATEGORY_NAME")
    private String role;

    @Column(name = "PERSONAL_NO")
    private String personalNo;

    @Column(name = "CEH_NO")
    private Integer cehNo;
}
