package com.todo.FPOS_project.db.repositories;

import com.todo.FPOS_project.db.models.Property;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface PropertyRepository extends MongoRepository<Property, String> {

    @Query("{ 'id' :  ?0 }")
    Property findByPropertyId(String id);

    @Query("{ 'agentId' :  ?0 }")
    List<Property> findByAgentId(String agentId);
    
    @Query("{ 'state' :  ?0 }")
    List<Property> findByState(String state);
    
    @Query("{ 'state' :  ?0, 'agentId' :  ?1 }")
    List<Property> findByStateAndAgentId(String state, String agentId);
}
