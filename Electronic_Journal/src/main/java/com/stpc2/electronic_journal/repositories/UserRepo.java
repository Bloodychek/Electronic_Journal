package com.stpc2.electronic_journal.repositories;

import com.stpc2.electronic_journal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findByPersonalNo (String personalNo);
}
