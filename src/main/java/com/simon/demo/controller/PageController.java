package com.simon.demo.controller;

import com.simon.demo.model.User;
import com.simon.october.annotation.RestController;
import com.simon.october.annotation.mvc.PostMapping;
import com.simon.october.annotation.mvc.RequestBody;

@RestController("/page")
public class PageController {

    @PostMapping("/user")
    public User page1(@RequestBody User user) {
        return user;
    }
}
