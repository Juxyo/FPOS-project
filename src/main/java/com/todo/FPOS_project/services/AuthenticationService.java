package com.todo.FPOS_project.services;

import com.todo.FPOS_project.db.models.User;
import com.todo.FPOS_project.db.models.submodels.Agent;
import com.todo.FPOS_project.db.models.submodels.Investor;
import com.todo.FPOS_project.db.repositories.UserRepository;
import com.todo.FPOS_project.dtos.request.AgentRegisterDTO;
import com.todo.FPOS_project.dtos.request.InvestorRegisterDTO;
import com.todo.FPOS_project.dtos.request.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class AuthenticationService {

    @Autowired
    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public void register(InvestorRegisterDTO input) {
        Investor user = new Investor();
        
        user.setNationalId(input.getNationalId());
        user.setFirstname(input.getFirstname());
        user.setLastname(input.getLastname());
        user.setEmailAdress(input.getEmailAdress());
        user.setPhoneNumber(input.getPhoneNumber());
        user.setAddress(input.getAddress());
        user.setHashedPassword(passwordEncoder.encode(input.getHashedPassword()));
        user.setEnabled(true);
        user.setBirthDate(input.getBirthDate());
        user.setCitizenship(input.getCitizenship());
        
        user.setBankAccountNumber(input.getBankAccountNumber());
        user.setBankName(input.getBankName());
        user.setIBAN(input.getIBAN());
        
        user.setEmployerAddress(input.getEmployerAddress());
        user.setEmployerName(input.getEmployerName());
        user.setJobTitle(input.getJobTitle());
        
        userRepository.save(user);
    }

    public void register(AgentRegisterDTO input) {
        Agent user = new Agent();

        user.setNationalId(input.getNationalId());
        user.setFirstname(input.getFirstname());
        user.setLastname(input.getLastname());
        user.setEmailAdress(input.getEmailAdress());
        user.setPhoneNumber(input.getPhoneNumber());
        user.setAddress(input.getAddress());
        user.setHashedPassword(passwordEncoder.encode(input.getHashedPassword()));
        user.setEnabled(true);
        user.setBirthDate(input.getBirthDate());

        userRepository.save(user);
    }

    public String generatePassword() {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String allCharacters = upperCaseLetters + lowerCaseLetters + digits;

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        password.append(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
        password.append(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));

        for (int i = 4; i < random.nextInt(16,25); i++) {
            password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }
        char[] array = password.toString().toCharArray();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            
            char temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }

        return new String(array);
    }

    public User authenticate(UserLoginDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmailAdress(),
                        input.getPassword()
                )
        );
        
        return userRepository.findByEmailAdress(input.getEmailAdress());
    }
}

