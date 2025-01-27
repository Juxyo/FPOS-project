package com.todo.FPOS_project.services;

import com.todo.FPOS_project.db.models.Transaction;
import com.todo.FPOS_project.db.repositories.TransactionRepository;
import com.todo.FPOS_project.db.repositories.UserRepository;
import com.todo.FPOS_project.enums.TransactionType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {
    
    TransactionRepository transactionRepository;
    UserRepository userRepository;
    WalletService walletService;
    
    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, WalletService walletService) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.walletService = walletService;
    }
    
    public Transaction getTransactionsById(String transactionId) {
        return transactionRepository.findTransaction(transactionId);
    }
    
    public List<Transaction> getTransactionsByInvestorId(String investorId) {
        return transactionRepository.findTransactions(investorId);
    }
    
    public List<Transaction> getTransactions(String investorId, String propertyId) {
        return transactionRepository.findTransactions(investorId, propertyId);
    }
    
    public Transaction createDepositTransaction(String investorId, double amount) {
        //Credit card payment
        
        Transaction transaction = new Transaction();
        transaction.setInvestorId(investorId);
        transaction.setAmount(amount);
        transaction.setType(TransactionType.DEPOSIT);
        transaction.setDate(LocalDate.now());
        
        transaction = transactionRepository.save(transaction);
        walletService.processTransaction(investorId, amount, TransactionType.DEPOSIT);
        return transaction;
    }
    
    public Transaction createWithdrawalTransaction(String investorId, double amount) {
        //Withdrawal to bank account
        
        Transaction transaction = new Transaction();
        transaction.setInvestorId(investorId);
        transaction.setAmount(amount);
        transaction.setType(TransactionType.WITHDRAWAL);
        transaction.setDate(LocalDate.now());
        
        transaction = transactionRepository.save(transaction);
        walletService.processTransaction(investorId, amount, TransactionType.WITHDRAWAL);
        return transaction;
    }
    
    public Transaction createBuyTransaction(String investorId, String propertyId, double amount) {
        Transaction transaction = new Transaction(investorId, propertyId, amount, TransactionType.BUY);
        
        walletService.processTransaction(investorId, amount, TransactionType.BUY);
        transaction = transactionRepository.save(transaction);
        
        return transaction;
    }
    
    public Transaction createSellTransaction(String investorId, String propertyId, double amount) {
        Transaction transaction = new Transaction(investorId, propertyId, amount, TransactionType.SELL);
        transaction = transactionRepository.save(transaction);
        
        walletService.processTransaction(investorId, amount, TransactionType.SELL);
        return transaction;
    }
    
    public Transaction createRentIncomeTransaction(String investorId, String propertyId, double amount) {
        Transaction transaction = new Transaction(investorId, propertyId, amount, TransactionType.RENT_INCOME);
        transaction = transactionRepository.save(transaction);
        
        walletService.processTransaction(investorId, amount, TransactionType.RENT_INCOME);
        return transaction;
    }
    
    public double getTotalBuyAmountLastYear(String investorId) {
        List<Transaction> transactions = transactionRepository.findBuyTransactionsByInvestorIdLastYear(investorId, LocalDate.now().minusYears(1).toString());
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }
    
    
}
