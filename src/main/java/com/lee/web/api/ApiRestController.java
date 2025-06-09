package com.lee.web.api;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/v1")
@RestController
public class ApiRestController {
    @GetMapping("/test")
    public ResponseEntity<?> test() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "api test success");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/fileUpload")
    public ResponseEntity<?> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        Map<String, String> response = new HashMap<>();
        String fileName = file.getName();
        String filePath = "/uploads/" + fileName; // Simulate file upload path

        File savedFile = new File(filePath);
        file.transferTo(savedFile);


        response.put("message", "file upload success");
        return ResponseEntity.ok(response);
    }
}
