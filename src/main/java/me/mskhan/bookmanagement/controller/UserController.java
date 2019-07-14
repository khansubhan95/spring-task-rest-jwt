package me.mskhan.bookmanagement.controller;

import me.mskhan.bookmanagement.entity.User;
import me.mskhan.bookmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public void signup(@RequestBody User theUser) {
        theUser.setPassword(bCryptPasswordEncoder.encode(theUser.getPassword()));
        userService.save(theUser);
    }

}
