package com.ecommerce.app.controller;

import com.ecommerce.app.domain.User;
import com.ecommerce.app.service.UserService;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/e-commerce")
public class UserController {
    private final UserService userService;

    public void createUser(@RequestBody @NotNull User user){
        this.userService.createUser(user);
    }

}
