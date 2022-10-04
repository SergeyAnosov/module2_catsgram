package ru.yandex.practicum.catsgram.controller;


import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    Map<String, User> users = new HashMap<>();

    @GetMapping("/users")
    public Map<String,User> findAll() {
        return users;
    }

    @PostMapping(value = "/users")
    public User createUser(@RequestBody User user) throws UserAlreadyExistException, InvalidEmailException {
        if (user.getEmail().isBlank() || user.getEmail().equals("") || user.getEmail() == null) {
            throw new InvalidEmailException("Email пустой");
        }
        if (!users.containsKey(user.getEmail())) {
            users.put(user.getEmail(), user);
            return user;
        } else {
            throw new UserAlreadyExistException("Пользователь уже существует");
        }
    }

    @PutMapping(value = "/users")
    public User updateUser(@RequestBody User user) throws InvalidEmailException {
        if (user.getEmail().isBlank() || user.getEmail().equals("") || user.getEmail() == null) {
            throw new InvalidEmailException("Email пустой");
        }
        if (users.containsKey(user.getEmail())) {
                users.remove(user.getEmail(), user);
                users.put(user.getEmail(), user);
                return user;
        } else {
            users.put(user.getEmail(), user);
            return user;
        }
    }
}

