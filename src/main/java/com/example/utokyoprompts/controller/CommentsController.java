package com.example.utokyoprompts.controller;

import com.example.utokyoprompts.entity.Comments;
import com.example.utokyoprompts.entity.Prompts;
import com.example.utokyoprompts.entity.Users;
import com.example.utokyoprompts.repository.CommentsRepository;
import com.example.utokyoprompts.repository.PromptsRepository;
import com.example.utokyoprompts.repository.UsersRepository;
import com.example.utokyoprompts.util.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
@RequestMapping(path = "/comments")
public class CommentsController {
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PromptsRepository promptsRepository;

    @PostMapping(path = "/users/{usersid}/prompts/{promptsid}")
    public @ResponseBody String addNewComments (@PathVariable Integer usersid, @PathVariable Integer promptsid, @RequestBody String content) {
        Comments n = new Comments();
        Optional<Users> usersOptional = usersRepository.findById(usersid);
        Optional<Prompts> promptsOptional = promptsRepository.findById(promptsid);

        if (usersOptional.isPresent() && promptsOptional.isPresent()) {
            n.setOwner(usersOptional.get());
            n.setPrompt(promptsOptional.get());
            n.setContent(content);
            commentsRepository.save(n);
            return MessageFormatter.formatMessage("New Comments saved!");
        } else {
            return MessageFormatter.formatMessage("Error: Incorrect userid or promptsid");
        }
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Comments> getAllComments () {
        return commentsRepository.findAll();
    }
}
