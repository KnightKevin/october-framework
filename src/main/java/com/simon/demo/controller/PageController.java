package com.simon.demo.controller;

import com.simon.october.annotation.RestController;
import com.simon.october.annotation.mvc.GetMapping;
import com.simon.october.annotation.mvc.PostMapping;

@RestController("/page")
public class PageController {

    @GetMapping("/user")
    public String page1() {
        return "index";
    }

    @GetMapping("/user1")
    public String page2(String id) {
        return id;
    }

    @GetMapping("/user2")
    public String page3() {
        return "index";
    }

    @PostMapping("/post/user3")
    public String page4() {
        return "index";
    }

    @PostMapping("/post/user4")
    public String page5() {
        return "index";
    }
}