package com.stpc2.electronic_journal.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

import static com.oracle.jrockit.jfr.FlightRecorder.isActive;

@Entity
@Data
@Table(name = "PERSONAL_STPC2", schema = "RML")
public class User{
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "SHORT_FIO")
    private String shortFIO;

    @Column(name = "PASSWORD")
    private String pass;

    @Column(name = "CATEGORY_NAME")
    private String role;

    @Column(name = "DIV_NO")
    private int divNo;
}
