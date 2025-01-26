package com.todo.FPOS_project.controllers;

import com.todo.FPOS_project.db.models.User;
import com.todo.FPOS_project.services.UserService;
import com.todo.FPOS_project.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/wallet")
@CrossOrigin(origins = "*")
@RestController
public class WalletController {
    
    @Autowired
    private final WalletService walletService;
    @Autowired
    private final UserService userService;
    
    public WalletController(WalletService walletService, UserService userService) {
        this.walletService = walletService;
        this.userService = userService;
    }
    
    @GetMapping("/{investorId}/balance")
    public ResponseEntity getBalance(@PathVariable String investorId) {
        if (investorId == null) return ResponseEntity.badRequest().body(Map.of("message", "Id is required."));

        if (!userService.userIdExists(investorId)) return ResponseEntity.badRequest().body(Map.of("message", "User does not exist."));
        if (!userService.isUserIdEnabled(investorId)) return ResponseEntity.badRequest().body(Map.of("message", "User is disabled."));
        if (!userService.isUserInvestor(investorId)) return ResponseEntity.badRequest().body(Map.of("message", "User is not an investor."));
        
        if (!walletService.walletExists(investorId)) walletService.createWallet(investorId);
        
        return ResponseEntity.ok(Map.of("balance", walletService.getBalanceByInvestorId(investorId)));
    }
    
}
