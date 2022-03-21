package com.hanghae99.w3blogproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/api/blogs/detail")
    public String detailBlog(@RequestParam("id") Long id) {
        return "/detail.html";
    }
}
