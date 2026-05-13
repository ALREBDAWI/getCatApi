package com.getcat.api.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.getcat.api.model.User;
import com.getcat.api.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;
    private final UserRepo userRepo;
    public String uploadFile(MultipartFile file, String email) throws IOException {
        User user = userRepo.findByEmail(email).orElseThrow();
        Map uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.emptyMap()
        );
        String imageUrl = uploadResult.get("secure_url").toString();
        user.setUserPhoto(imageUrl);
        userRepo.save(user);
        System.out.println("upload success: " + uploadResult);
        return imageUrl;
    }
}
