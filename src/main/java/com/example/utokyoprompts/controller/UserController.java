package com.example.utokyoprompts.controller;

import com.example.utokyoprompts.entity.Users;
import com.example.utokyoprompts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser (@RequestParam String name) {
        Users n = new Users();
        n.setName(name);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Users> getAllUsers() {
        return userRepository.findAll();
    }

}
