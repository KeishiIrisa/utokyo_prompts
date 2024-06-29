package com.example.utokyoprompts.controller;

import com.example.utokyoprompts.entity.Users;
import com.example.utokyoprompts.repository.UsersRepository;
import com.example.utokyoprompts.util.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping(path = "/users")
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;

    @PostMapping(path = "/{name}")
    public @ResponseBody String addNewUser (@PathVariable String name) {
        Users n = new Users();
        n.setName(name);
        usersRepository.save(n);

        String formattedMessage = MessageFormatter.formatMessage("New User Saved!");
        return formattedMessage;
    }

    @DeleteMapping (path = "/{id}")
    public @ResponseBody String deleteUser(@PathVariable Integer id) {
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

    @GetMapping(path = "/{id}")
    public @ResponseBody Users getUserById(@PathVariable Integer id) {
        Optional<Users> usersOptional = usersRepository.findById(id);
        if (usersOptional.isPresent()) {
            return usersOptional.get();
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("Not found user with %d", id)
            );
        }
    }

}
