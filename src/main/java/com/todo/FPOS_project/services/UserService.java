package com.todo.FPOS_project.services;

import com.todo.FPOS_project.db.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public boolean userIdExists(String nationalId) {
        return userRepository.findByNationalId(nationalId) != null;
    }
    
    public boolean userEmailExists(String emailAdress) {
        return userRepository.findByEmailAdress(emailAdress) != null;
    }
    
    public boolean isUserIdEnabled(String nationalId) {
        return userRepository.findByNationalId(nationalId).isEnabled();
    }
    
    public boolean isUserEmailEnabled(String emailAdress) {
        return userRepository.findByEmailAdress(emailAdress).isEnabled();
    }
}
