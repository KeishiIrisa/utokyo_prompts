package com.example.utokyoprompts.controller;

import com.example.utokyoprompts.entity.Lessons;
import com.example.utokyoprompts.entity.UserLesson;
import com.example.utokyoprompts.entity.Users;
import com.example.utokyoprompts.repository.LessonsRepository;
import com.example.utokyoprompts.repository.UserLessonRepository;
import com.example.utokyoprompts.repository.UsersRepository;
import com.example.utokyoprompts.util.MessageFormatter;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/user-lesson")
public class UserLessonController {
    @Autowired
    private UserLessonRepository userLessonRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private LessonsRepository lessonsRepository;

    @PostMapping(path = "/users/{userid}/lessons/{lessonid}")
    public @ResponseBody String addUserLesson (@PathVariable Integer userid, @PathVariable Integer lessonid) {
        UserLesson n = new UserLesson();

        // UserとLessonオブジェクトをidによって作成
        Optional<Users> usersOptional = usersRepository.findById(userid);
        Optional<Lessons> lessonsOptional = lessonsRepository.findById(lessonid);

        if (usersOptional.isPresent() && lessonsOptional.isPresent()) {
            Users user = usersOptional.get();
            Lessons lesson = lessonsOptional.get();

            n.setUser(user);
            n.setLesson(lesson);
            userLessonRepository.save(n);
            String formattedMessage = MessageFormatter.formatMessage("UserLesson Saved!");
            return formattedMessage;
        } else {
            String formattedMessage = MessageFormatter.formatMessage("User or Lesson not found");
            return formattedMessage;
        }

    }

    @DeleteMapping(path = "/users/{userid}/lessons/{lessonid}")
    public @ResponseBody String deleteUserLesson (@PathVariable Integer userid, @PathVariable Integer lessonid) {
        Optional<Users> usersOptional = usersRepository.findById(userid);
        Optional<Lessons> lessonsOptional = lessonsRepository.findById(lessonid);
        if (usersOptional.isPresent() && lessonsOptional.isPresent()) {
            Users user = usersOptional.get();
            Lessons lesson = lessonsOptional.get();
            Optional<UserLesson> userLessonOptional = userLessonRepository.findByUserAndLesson(user, lesson);
            if (userLessonOptional.isPresent()) {
                UserLesson userLesson = userLessonOptional.get();
                userLessonRepository.delete(userLesson);
                return MessageFormatter.formatMessage("UserLesson deleted!");
            } else {
                return MessageFormatter.formatMessage("Error: No such a field..");
            }
        } else {
            return MessageFormatter.formatMessage("Error: No such a user or lesson..");
        }
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<UserLesson> getAllUserLessons () {
        return userLessonRepository.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public @ResponseBody List<Lessons> getLessonsByUserId (@PathVariable Integer id) {
        Optional<Users> usersOptional = usersRepository.findById(id);
        if (usersOptional.isPresent()) {
            Users user = usersOptional.get();
            List<UserLesson> userLessons = userLessonRepository.findByUser(user);
            return userLessons.stream().map(UserLesson::getLesson).collect(Collectors.toList());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("Not found user with %d", id)
            );
        }
    }

    @GetMapping(path = "/lessons/{id}")
    public @ResponseBody List<Users> getUsersByLessonId (@PathVariable Integer id) {
        Optional<Lessons> lessonsOptional = lessonsRepository.findById(id);
        if (lessonsOptional.isPresent()) {
            Lessons lesson = lessonsOptional.get();
            List<UserLesson> userlessons = userLessonRepository.findByLesson(lesson);
            return userlessons.stream().map(UserLesson::getUser).collect(Collectors.toList());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("Not found lesson with %d", id)
            );
        }
    }
}
