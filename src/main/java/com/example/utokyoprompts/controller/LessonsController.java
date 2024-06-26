package com.example.utokyoprompts.controller;

import com.example.utokyoprompts.entity.Lessons;
import com.example.utokyoprompts.repository.LessonsRepository;
import com.example.utokyoprompts.util.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/lessons")
public class LessonsController {
    @Autowired
    private LessonsRepository lessonsRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewLesson (@RequestParam String name, @RequestParam String category) {
        Lessons n = new Lessons();
        n.setName(name);
        n.setCategory(category);
        lessonsRepository.save(n);

        String formattedMessage = MessageFormatter.formatMessage("New Lesson Saved!");
        return formattedMessage;
    }

    @PostMapping(path = "/delete")
    public @ResponseBody String deleteLesson(@RequestParam Integer id) {
        Optional<Lessons> lessonsOptional = lessonsRepository.findById(id);
        if (lessonsOptional.isPresent()) {
            try {
                lessonsRepository.deleteById(id);
                String message = String.format("Lesson with id %d deleted!", id);
                return MessageFormatter.formatMessage(message);
            } catch (Exception e) {
                return "Error: Unexpected error occurred while deleting lesson with id " + id;
            }
        } else {
            return "Error: Lesson with id " + id + " not found";
        }
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Lessons> getAllLessons () {
        return lessonsRepository.findAll();
    }
}
