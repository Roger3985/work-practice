package com.roger.zk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/zk")
public class ZKController {

    @GetMapping("/hello")
    public String hello() {
        return null;
    }

}
