package com.todo.FPOS_project.controllers;

import com.todo.FPOS_project.db.models.User;
import com.todo.FPOS_project.db.models.submodels.Agent;
import com.todo.FPOS_project.db.models.submodels.Investor;
import com.todo.FPOS_project.db.repositories.UserRepository;
import com.todo.FPOS_project.dtos.request.AgentRegisterDTO;
import com.todo.FPOS_project.dtos.request.InvestorRegisterDTO;
import com.todo.FPOS_project.dtos.request.UserLoginDTO;
import com.todo.FPOS_project.dtos.response.UserLoginResponseDTO;
import com.todo.FPOS_project.services.AuthenticationService;
import com.todo.FPOS_project.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@RestController
public class AuthenticationController {
    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final AuthenticationService authenticationService;
    @Autowired
    private final UserRepository userRepository;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
    }

    @PostMapping("/investors/register")
    public ResponseEntity register(@RequestBody InvestorRegisterDTO investorRegisterDTO) {
        if (investorRegisterDTO.getEmailAdress() == null || investorRegisterDTO.getNationalId() == null) return ResponseEntity.badRequest().body(Map.of("message", "Email and national ID are required"));
        
        User user = userRepository.findByEmailAdress(investorRegisterDTO.getEmailAdress());
        if (user != null) ResponseEntity.ok(Map.of("message", "User already exists"));
        
        authenticationService.register(investorRegisterDTO);
        return ResponseEntity.ok(Map.of("message", "Registered successfully"));
    }

    @PostMapping("/agents/register")
    public ResponseEntity register(@RequestBody AgentRegisterDTO agentRegisterDTO) {
        if (agentRegisterDTO.getEmailAdress() == null || agentRegisterDTO.getNationalId() == null) return ResponseEntity.badRequest().body(Map.of("message", "Email and national ID are required"));

        User user = userRepository.findByEmailAdress(agentRegisterDTO.getEmailAdress());
        if (user != null) ResponseEntity.ok(Map.of("message", "User already exists"));

        authenticationService.register(agentRegisterDTO);
        return ResponseEntity.ok(Map.of("message", "Registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody UserLoginDTO loginUserDto) {
        try {
            if (loginUserDto.getEmailAdress() == null || loginUserDto.getPassword() == null) return ResponseEntity.badRequest().body("Email and password are required");
            
            User user = userRepository.findByEmailAdress(loginUserDto.getEmailAdress());
            if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "User not found"));
            if (!user.isEnabled()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "User is disabled"));

            User authenticatedUser = authenticationService.authenticate(loginUserDto);

            String jwtToken = jwtService.generateToken(authenticatedUser);

            UserLoginResponseDTO loginResponse = new UserLoginResponseDTO().setValue(jwtToken).setExpiresAt(LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis() + jwtService.getExpirationTime()), ZoneId.systemDefault()));

            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/logout")
    public ResponseEntity logout(@RequestHeader(name="Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        if (token == null) return ResponseEntity.badRequest().body(Map.of("message", "Authorization header is missing"));

        try {
            jwtService.blacklistToken(token);

            return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}

