package com.revature.controller;


import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.service.LoginService;
import com.revature.service.PortfolioService;
import com.revature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin(origins = "http:172.17.14.49:4200", allowCredentials = "true")
@SessionScope
@RequestMapping(value = "/user")
public class UserController {

    private HttpSession httpSession;
    private UserService userService;
    private LoginService loginService;
    private PortfolioService portfolioService;
    private User user;
    private UserDTO userDto;

    @Autowired
    public UserController(
            User user,
            UserDTO userDto,
            UserService userService,
            LoginService loginService,
            HttpSession httpSession,
            PortfolioService portfolioService) {
        this.userDto = userDto;
        this.user = user;
        this.userService = userService;
        this.loginService = loginService;
        this.httpSession = httpSession;
        this.portfolioService = portfolioService;
    }

    @GetMapping
    public List<User> findAllUsers() {

        System.out.println(this.httpSession.getAttribute("user"));
        return userService.findAllUsers();

    }

    @GetMapping("/{id}")
    public User oneUser(@PathVariable("id") int id) {
        User user = userService.findById(id);
        return user;
    }

    @PostMapping
    public ResponseEntity<List<User>> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.status(201).body(userService.findAllUsers());
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody UserDTO user) {
        if (userService.updateUser(user)) {
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }

//	@CrossOrigin(origins = "http://18.219.112.104:4200/login")
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDto) {
        userDto = loginService.login(userDto);
        if (userDto != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/logout")
    public ResponseEntity logout() {
        this.loginService.logout();
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();

    }


}
