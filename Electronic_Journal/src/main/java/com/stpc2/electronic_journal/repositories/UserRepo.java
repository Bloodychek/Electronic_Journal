package com.stpc2.electronic_journal.repositories;

import com.stpc2.electronic_journal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    /**
     * Method searchers for a person by his personal number
     * @param personalNo - Params that contains personal number
     * @return
     */
    User findByPersonalNo (String personalNo);
}
