package com.roger.master.controller;

import com.roger.master.service.MasterService;
import com.roger.user.dto.UserDto;
import com.roger.user.pojo.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "controller block")
@RestController
@RequestMapping("/master")
public class MasterController {

    @Autowired
    MasterService masterService;

    @PostMapping("/register")
    public Result register(@Valid @RequestBody UserDto userDto) {
        return masterService.UserWithDepartment(userDto);
    }
}
