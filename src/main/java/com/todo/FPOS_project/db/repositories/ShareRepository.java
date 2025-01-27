package com.todo.FPOS_project.db.repositories;

import com.todo.FPOS_project.db.models.Share;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface ShareRepository extends MongoRepository<Share, String> {
    
    @Query("{investorId: '?0', propertyId: '?1'}")
    Share findByInvestorAndProperty(String investorId, String propertyId);
    
    @Query("{investorId: '?0'}")
    List<Share> findByInvestorId(String investorId);
    
    @Query("{propertyId: '?0'}")
    List<Share> findByPropertyId(String propertyId);
}
