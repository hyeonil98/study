package com.lee.web.user.controller;

import com.lee.web.user.domain.User;
import com.lee.web.user.domain.UserRegisterDto;
import com.lee.web.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RequiredArgsConstructor
@RestController
public class UserRestController {
    private final UserService userService;

    @GetMapping(value = "/api/v1/getUser/{userId}")
    public ResponseEntity<?> getUser(@PathVariable String userId) {
        User user = userService.getUserId(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/api/v1/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDto dto) {
        userService.register(dto);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping(value = "/api/v1/excelDownload")
    public ResponseEntity<?> excelDownload() {
        File file = new File("C:\\Users\\lee\\Desktop\\<UNK>.xlsx");
        // Implement the logic for Excel download
        return ResponseEntity.ok("Excel download initiated");
    }
}
