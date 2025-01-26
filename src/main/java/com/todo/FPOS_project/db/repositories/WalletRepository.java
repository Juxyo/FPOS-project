package com.todo.FPOS_project.db.repositories;

import com.todo.FPOS_project.db.models.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface WalletRepository extends MongoRepository<Wallet, String> {
    
    @Query("{ 'investorId' : ?0 }")
    Wallet findByInvestorId(String investorId);
}
