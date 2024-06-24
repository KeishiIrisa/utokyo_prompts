package com.example.utokyoprompts.controller;

import com.example.utokyoprompts.entity.Users;
import com.example.utokyoprompts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser (@RequestParam String name) {
        Users n = new Users();
        n.setName(name);
        n.setCreated_at(LocalDateTime.now());
        userRepository.save(n);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        return "Saved! Current time: " + formattedDateTime;
    }

    @PostMapping(path = "/delete")
    public @ResponseBody String deleteUser(@RequestParam Integer id) {
        Optional<Users> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            try {
                userRepository.deleteById(id);
                return "User with id " + id + " deleted!";
            } catch (Exception e) {
                return "Error: Unexpected error occurred while deleting user with id " + id;
            }
        } else {
            return "Error: User with id " + id + " not found";
        }

    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Users> getAllUsers() {
        return userRepository.findAll();
    }

}
