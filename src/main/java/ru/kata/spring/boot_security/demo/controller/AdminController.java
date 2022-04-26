package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin-users";
    }

    @GetMapping("/users/{user}")
    public String userInfo(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        return "admin-user-info";
    }

    @GetMapping("/users/add")
    public String userAddPage() {
        return "admin-add-user";
    }

    @PostMapping("/users/add")
    public String userAdd(@ModelAttribute User user, @RequestParam(name = "role") List<String> roles) {
        userService.addUser(user, roles);
        return "redirect:/admin";
    }

    @GetMapping(value = "users/edit/{id}")
    public String editUser(ModelMap model, @PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin-edit-user";
    }

    @PostMapping(value = "users/edit")
    public String edit(@ModelAttribute("user") User user, @RequestParam(name = "role") List<String> roles) {
        userService.editUser(user, roles);
        return "redirect:/admin";
    }

    @PostMapping("users/delete")
    public String deleteUserById(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}