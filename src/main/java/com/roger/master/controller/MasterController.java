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
import org.springframework.web.bind.annotation.*;

@Tag(name = "controller block")
@RestController
@RequestMapping("/master")
public class MasterController {

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
}
