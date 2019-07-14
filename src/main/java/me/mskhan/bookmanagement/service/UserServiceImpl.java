package me.mskhan.bookmanagement.service;

import me.mskhan.bookmanagement.dao.UserRepository;
import me.mskhan.bookmanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User theUser) {
        User existingUser = userRepository.findByUsername(theUser.getUsername());
        if (existingUser != null) {
            throw new RuntimeException("User with username already exists");
        }
        userRepository.save(theUser);
    }

    @Override
    public User findByUsername(String theUsername) {
        return userRepository.findByUsername(theUsername);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User theUser = userRepository.findByUsername(s);
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        grantedAuthoritySet.add(new SimpleGrantedAuthority("ANONYMOUS"));
        return new org.springframework.security.core.userdetails.User(theUser.getUsername(), theUser.getPassword(), grantedAuthoritySet);
    }
}
