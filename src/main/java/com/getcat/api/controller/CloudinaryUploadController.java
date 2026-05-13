package com.getcat.api.controller;

import com.getcat.api.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/api/test")
@RestController
@RequiredArgsConstructor
public class CloudinaryUploadController {
    private final CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file,
                                         Authentication authentication
    ) throws IOException {
        System.out.println("request arrived");
        System.out.println(file.getOriginalFilename());
        String email = authentication.getName();
        String imageUrl = cloudinaryService.uploadFile(file, email);
        return ResponseEntity.ok(imageUrl);
    }

}
