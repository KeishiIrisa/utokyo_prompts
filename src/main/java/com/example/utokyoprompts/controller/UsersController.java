package com.example.utokyoprompts.controller;

import com.example.utokyoprompts.entity.Users;
import com.example.utokyoprompts.repository.UsersRepository;
import com.example.utokyoprompts.util.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping(path = "/user")
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser (@RequestParam String name) {
        Users n = new Users();
        n.setName(name);
        n.setCreated_at(LocalDateTime.now());
        usersRepository.save(n);

        String formattedMessage = MessageFormatter.formatMessage("New User Saved!");
        return formattedMessage;
    }

    @PostMapping(path = "/delete")
    public @ResponseBody String deleteUser(@RequestParam Integer id) {
        Optional<Users> userOptional = usersRepository.findById(id);
        if (userOptional.isPresent()) {
            try {
                usersRepository.deleteById(id);
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
        return usersRepository.findAll();
    }

}
