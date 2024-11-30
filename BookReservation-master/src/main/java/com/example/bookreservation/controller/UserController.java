package com.example.bookreservation.controller;

import com.example.bookreservation.model.input.UserDtoInput;
import com.example.bookreservation.model.output.UserDtoOutput;
import com.example.bookreservation.model.output.UserDtoOutputForUser;
import com.example.bookreservation.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get/all")
    public List<UserDtoOutput> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/get/all/for/user")
    public List<UserDtoOutputForUser> getAllUsersForUser() {
        return userService.getAllUsersForUser();
    }

    @GetMapping("/find/by/{finCode}")
    public UserDtoOutput findByFinCode(@PathVariable String finCode) {
        return userService.findByFinCode(finCode);
    }

    @GetMapping("/find/by/finCode/for/user")
    public UserDtoOutputForUser findByFinCodeForUser(@RequestParam String finCode) {
        return userService.findByFinCodeForUser(finCode);
    }

    @PostMapping("/save")
    public void saveCustomer(@RequestBody UserDtoInput userDtoInput) {
        userService.saveUser(userDtoInput);
    }

    @DeleteMapping("/delete/by/{finCode}")
    public void deleteByFinCode(@PathVariable String finCode){
        userService.deleteByFinCode(finCode);
    }
}
