package com.example;

import com.example.entity.SecurityUser;
import com.example.repo.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class DatabaseUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    public DatabaseUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userOptional = this.userRepository.findByUsername(username);
        return userOptional.map(SecurityUser::new).orElseThrow();
    }
}
