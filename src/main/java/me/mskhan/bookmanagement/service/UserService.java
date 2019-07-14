package me.mskhan.bookmanagement.service;

import me.mskhan.bookmanagement.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public void save(User theUser);
    public User findByUsername(String theUsername);
}
