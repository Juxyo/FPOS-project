package com.todo.FPOS_project.db.repositories;

import com.todo.FPOS_project.db.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
    
    @Query("{ 'transactionId' : ?0 }")
    Transaction findTransaction(String transactionId);
    
    @Query("{ 'investorId' : ?0 }")
    List<Transaction> findTransactions(String investorId);
    
    @Query("{'investorId' : ?0, 'propertyId' : ?1}")
    List<Transaction> findTransactions(String investorId, String propertyId);
    
    @Query("{ 'investorId' : ?0, 'type' : ?1 }")
    List<Transaction> findTransactionsByInvestorIdAndType(String investorId, String type);
    
    @Query("{ 'investorId' : ?0, 'type' : 'BUY', 'date' : { $gt : ?1 } }")
    List<Transaction> findBuyTransactionsByInvestorIdLastYear(String investorId, String date);
}
