package com.simon.demo.controller;

import com.simon.october.annotation.RestController;
import com.simon.october.annotation.mvc.GetMapping;

@RestController("/page")
public class PageController {

    @GetMapping("/user")
    public String page1() {
        return "index";
    }
}
