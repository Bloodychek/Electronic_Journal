package com.stpc2.electronic_journal.serviceImpl;

import com.stpc2.electronic_journal.models.User;
import com.stpc2.electronic_journal.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String shortFio){
        User user = userRepo.findByShortFIO(shortFio);
        UserService userService = null;
        if(user !=null)
        {
            userService = new UserService();
            userService.setUser(user);
        }
        return userService;
    }
}
