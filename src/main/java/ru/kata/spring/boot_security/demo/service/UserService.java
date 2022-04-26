package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user, List<String> roles);

    void deleteUser(Long id);

    void editUser(User user, List<String> roles);

    User getUserById(Long id);

    User getUserByEmail(String email);

    List<User> getAllUsers();
}
