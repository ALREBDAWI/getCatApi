package com.getcat.api.auth;


import com.getcat.api.config.JwtService;
import com.getcat.api.model.Role;
import com.getcat.api.model.RoleEnum;
import com.getcat.api.model.User;
import com.getcat.api.repo.RoleRepo;
import com.getcat.api.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    // RegisterRequest is a dto class that makes the user send name, email, and password
    public AuthenticationResponse register(RegisterRequest request) {
        // I used the enum to make sure that USER is written like DB, but we can delete enum from project for more flexibility
        var userRole = roleRepo.findByRoleLabel(RoleEnum.USER.name())
                .orElseThrow(() -> new RuntimeException("Error: Role is not found in database."));
        // here we build a user object from the attributes given in RegisterRequest
        var user = User.builder()
                .isBlocked(false)
                .isDeleted(false)
                .isOnline(false)
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                // we hash the password using passwordEncoder using BCrypt
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                // we have an enum for roles, either client or admin
                .role(userRole)
                .build();
        // save the new user in database
        userRepo.save(user);
        System.out.println("new user added to DB");

        // token will be generated using JWT and the user will be authenticated after registration
        var jwtToken = jwtService.generateToken(user);
        // return the new token
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        var user = userRepo.findByEmail(request.getEmail()).orElseThrow();
        // we can change data type later
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
