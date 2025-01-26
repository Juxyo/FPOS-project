package com.todo.FPOS_project.services;

import com.todo.FPOS_project.db.models.Wallet;
import com.todo.FPOS_project.db.repositories.UserRepository;
import com.todo.FPOS_project.db.repositories.WalletRepository;
import com.todo.FPOS_project.enums.TransactionType;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
        
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public WalletService(WalletRepository walletRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }
    
    public boolean walletExists(String investorId) {
        return walletRepository.findByInvestorId(investorId) != null;
    }
    
    public void createWallet(String investorId) {
        Wallet wallet = new Wallet(investorId, 0);
        walletRepository.save(wallet);
    }
    
    public double getBalanceByInvestorId(String investorId) {
        return walletRepository.findByInvestorId(investorId).getBalance();
    }
    
    public void addCurrency(String investorId, double amount) {
        Wallet wallet = walletRepository.findByInvestorId(investorId);
        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);
    }
    
    public void removeCurrency(String investorId, double amount) {
        Wallet wallet = walletRepository.findByInvestorId(investorId);
        
        if (wallet.getBalance() < amount) throw new IllegalArgumentException("Insufficient funds");
        
        wallet.setBalance(wallet.getBalance() - amount);
        walletRepository.save(wallet);
    }
    
    public void processTransaction(String investorId, double amount, TransactionType transactionType) {
        switch (transactionType) {
            case DEPOSIT:
            case SELL:
            case RENT_INCOME:
                addCurrency(investorId, amount);
                break;
            case WITHDRAWAL:
            case BUY:
                removeCurrency(investorId, amount);
                break;
            default:
                break;
        }
    }
}
