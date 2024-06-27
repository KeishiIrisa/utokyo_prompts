package com.example.utokyoprompts.controller;

import com.example.utokyoprompts.entity.Lessons;
import com.example.utokyoprompts.entity.Prompts;
import com.example.utokyoprompts.entity.Users;
import com.example.utokyoprompts.repository.LessonsRepository;
import com.example.utokyoprompts.repository.PromptsRepository;
import com.example.utokyoprompts.repository.UsersRepository;
import com.example.utokyoprompts.util.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/prompts")
public class PromptsController {
    @Autowired
    private PromptsRepository promptsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private LessonsRepository lessonsRepository;

    @PostMapping(path = "/users/{userid}/lessons/{lessonid}")
    public @ResponseBody String addNewPrompts (@PathVariable Integer userid, @PathVariable Integer lessonid, @RequestBody String prompts) {
        Prompts n = new Prompts();
        Optional<Users> usersOptional = usersRepository.findById(userid);
        Optional<Lessons> lessonsOptional = lessonsRepository.findById(lessonid);

        if (usersOptional.isPresent() && lessonsOptional.isPresent()) {
            n.setOwner(usersOptional.get());
            n.setLesson(lessonsOptional.get());
            n.setPrompts(prompts);
            promptsRepository.save(n);
            return MessageFormatter.formatMessage("New Prompts saved !");
        } else {
            return MessageFormatter.formatMessage("Error: Incorrect userid or lessonid");
        }
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Prompts> getAllPrompts() {
        return promptsRepository.findAll();
    }
}
