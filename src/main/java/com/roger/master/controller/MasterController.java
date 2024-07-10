package com.roger.master.controller;

import com.roger.master.service.MasterService;
import com.roger.user.dto.UserDto;
import com.roger.user.pojo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Tag(name = "controller block")
@RestController
@RequestMapping("/master")
public class MasterController {
    @Value("${spring.datasource.password}")
    private String password;
    @Autowired
    MasterService masterService;

    /**
     * 會員註冊 (增)
     */
    @Operation(
            summary = "會員註冊",
            description = "透過此路徑註冊新的會員"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "狀態碼正常", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "401", description = "沒有權限", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "404", description = "找不到路徑", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class)))
    })
    @PostMapping("/register")
    public Result register(@Valid @RequestBody UserDto userDto) {
        return masterService.UserWithDepartment(userDto);
    }

    /**
     * 查詢員工與所在部門相關訊息 (查)
     */
    @Operation(
            summary = "查詢員工與所在部門相關訊息 (查)",
            description = "透過此路徑查詢員工與所在部門相關訊息"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "狀態碼正常", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "401", description = "沒有權限", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "404", description = "找不到路徑", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)))
    })
    @GetMapping("/searchUserWithDepartment")
    public UserDto searchUserWithDepartment(@Valid @RequestBody UserDto userDto) {
        int i = 1 / 0;
        return masterService.findByUserNameWithDepartment(userDto);
    }
}
