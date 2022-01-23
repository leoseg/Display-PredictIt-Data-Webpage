package com.webpage.predictpoliticalpartyprice.services;

import com.webpage.predictpoliticalpartyprice.dao.UserRepository;
import com.webpage.predictpoliticalpartyprice.entities.User;
import com.webpage.predictpoliticalpartyprice.security.CustomUserDetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private UserRepository userRepo;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByuserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
}
