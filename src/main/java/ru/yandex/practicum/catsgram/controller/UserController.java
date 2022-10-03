package ru.yandex.practicum.catsgram.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    List<User> users = new ArrayList<>();

    @GetMapping("/users")
    public List<User> findAll() {
        return users;
    }

    @PostMapping(value = "/user")
    public User createUser(@RequestBody User user) throws UserAlreadyExistException, InvalidEmailException {
       if (!users.contains(user)) {
           if (user.getEmail().isBlank()) {
               throw new InvalidEmailException("Email пустой");
           } else {
               users.add(user);
               return user;
           }
       } else {
           throw new UserAlreadyExistException("Пользователь уже существует");
       }
    }

}

