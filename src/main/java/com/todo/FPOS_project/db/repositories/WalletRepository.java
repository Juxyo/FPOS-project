package com.todo.FPOS_project.db.repositories;

import com.todo.FPOS_project.db.models.User;
import com.todo.FPOS_project.db.models.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface WalletRepository extends MongoRepository<Wallet, String> {
    
    @Query("{ 'investorId' : ?0 }")
    Wallet findByInvestorId(String investorId);
    
    @Query(value = "{ 'investorId' : ?0 }", fields = "{ 'balance' : 1 }")
    double getBalanceByInvestorId(String investorId);
}
