package com.todo.FPOS_project.db.repositories;

import com.todo.FPOS_project.db.models.Property;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface PropertyRepository extends MongoRepository<Property, String> {

    @Query("{ 'id' :  ?0 }")
    Property findByRepositoryId(String id);

    @Query(value = "{'id' :  ?0}", fields = "{ 'estimatedValue' :  1}")
    double getEstimatedValueByRepositoryId(String id);
}
