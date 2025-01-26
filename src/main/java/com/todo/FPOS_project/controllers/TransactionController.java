package com.todo.FPOS_project.controllers;

import com.todo.FPOS_project.db.models.Transaction;
import com.todo.FPOS_project.dtos.request.TransactionOrderDTO;
import com.todo.FPOS_project.services.TransactionsService;
import com.todo.FPOS_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/transaction")
@CrossOrigin(origins = "*")
@RestController
public class TransactionController {
    
    @Autowired
    private final TransactionsService transactionService;
    
    @Autowired
    private final UserService userService;
    
    public TransactionController(TransactionsService transactionService, UserService userService) {
        this.transactionService = transactionService;
        this.userService = userService;
    }
    
    @PostMapping("/deposit")
    public ResponseEntity deposit(@RequestBody TransactionOrderDTO transactionOrderDTO) {
        if (transactionOrderDTO.getInvestorId() == null) return ResponseEntity.badRequest().body("Investor ID is required.");
        
        if (!userService.userIdExists(transactionOrderDTO.getInvestorId())) return ResponseEntity.badRequest().body("User does not exist.");
        if (!userService.isUserIdEnabled(transactionOrderDTO.getInvestorId())) return ResponseEntity.badRequest().body("User is disabled.");
        
        Transaction transaction = transactionService.createDepositTransaction(transactionOrderDTO.getInvestorId(), transactionOrderDTO.getAmount());
        return ResponseEntity.ok(Map.of("transaction", transaction));
    }
    
    @PostMapping("/withdraw")
    public ResponseEntity withdraw(@RequestBody TransactionOrderDTO transactionOrderDTO) {
        if (transactionOrderDTO.getInvestorId() == null) return ResponseEntity.badRequest().body("Investor ID is required.");
        
        if (!userService.userIdExists(transactionOrderDTO.getInvestorId())) return ResponseEntity.badRequest().body("User does not exist.");
        if (!userService.isUserIdEnabled(transactionOrderDTO.getInvestorId())) return ResponseEntity.badRequest().body("User is disabled.");
        
        try {
            Transaction transaction = transactionService.createWithdrawalTransaction(transactionOrderDTO.getInvestorId(), transactionOrderDTO.getAmount());

            return ResponseEntity.ok(Map.of("transaction", transaction));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}
