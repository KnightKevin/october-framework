package com.simon.demo.controller;

import com.simon.demo.component.Component1;
import com.simon.october.annotation.Autowired;
import com.simon.october.annotation.RestController;
import com.simon.october.annotation.mvc.GetMapping;
import com.simon.october.annotation.mvc.PostMapping;
import com.simon.october.annotation.mvc.RequestParam;

import java.util.HashMap;
import java.util.Map;

@RestController("/page1")
public class Page1Controller {

    @Autowired
    private Component1 component1;

    @GetMapping("/user")
    public Map<String, Object> page1(@RequestParam(value = "name") String name, @RequestParam(value = "age", require = true) int age) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("age", age);
        component1.display();
        return map;
    }

    @GetMapping("/user1")
    public String page2() {
        return "index";
    }

    @GetMapping("/user2")
    public String page3() {
        return "index";
    }

    @PostMapping("/user3")
    public String page4() {
        return "index";
    }

    @PostMapping("/user4")
    public String page5() {
        return "index";
    }
}
