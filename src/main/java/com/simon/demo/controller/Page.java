package com.simon.demo.controller;

import com.simon.october.annotation.RestController;

@RestController
public class Page {
    public String page1() {
        return "index";
    }
}
