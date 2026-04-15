package com.getcat.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/me")
public class Test {
    @GetMapping("/message")
    public String message() {
        return "hello spring boot";
    }
    @GetMapping("/list")
    public List<String> task(){
        return Arrays.asList("hello spring","good bye JEE");
    }
    @GetMapping("/task")
    public Map<String, String> getTask() {
        Map<String, String> task = new HashMap<>();
        task.put("id", "1");
        task.put("title", "sprong boot");
        return task;
    }
}
