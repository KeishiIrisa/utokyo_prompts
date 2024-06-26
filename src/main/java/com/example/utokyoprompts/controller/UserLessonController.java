package com.example.utokyoprompts.controller;

import com.example.utokyoprompts.entity.Lessons;
import com.example.utokyoprompts.entity.UserLesson;
import com.example.utokyoprompts.entity.Users;
import com.example.utokyoprompts.repository.LessonsRepository;
import com.example.utokyoprompts.repository.UserLessonRepository;
import com.example.utokyoprompts.repository.UsersRepository;
import com.example.utokyoprompts.util.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/user-lesson")
public class UserLessonController {
    @Autowired
    private UserLessonRepository userLessonRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private LessonsRepository lessonsRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addUserLesson (@RequestParam Integer userid, @RequestParam Integer lessonid) {
        UserLesson n = new UserLesson();

        // UserとLessonオブジェクトをidによって作成
        Optional<Users> usersOptional = usersRepository.findById(userid);
        Optional<Lessons> lessonsOptional = lessonsRepository.findById(lessonid);

        if (usersOptional.isPresent() && lessonsOptional.isPresent()) {
            Users user = usersOptional.get();
            Lessons lesson = lessonsOptional.get();

            n.setUser_id(user);
            n.setLesson_id(lesson);
            userLessonRepository.save(n);
            String formattedMessage = MessageFormatter.formatMessage("UserLesson Saved!");
            return formattedMessage;
        } else {
            String formattedMessage = MessageFormatter.formatMessage("User or Lesson not found");
            return formattedMessage;
        }

    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<UserLesson> getAllUserLessons () {
        return userLessonRepository.findAll();
    }
}
