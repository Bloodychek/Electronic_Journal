package com.stpc2.electronic_journal.serviceImpl;

import com.stpc2.electronic_journal.models.User;
import com.stpc2.electronic_journal.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Class that implements the methods of the UserDetailsService
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;

    /**
     * Method that checks the correctness of the data when entering the login field
     * @param personalNo the username identifying the user whose data is required.
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String personalNo) {
        User user = userRepo.findByPersonalNo(personalNo);
        UserService userService = null;
        if (user != null) {
            userService = new UserService();
            userService.setUser(user);
        }
        return userService;
    }
}
