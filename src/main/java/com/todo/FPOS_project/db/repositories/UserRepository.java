package com.todo.FPOS_project.db.repositories;

import com.todo.FPOS_project.db.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    
    @Query("{ 'emailAdress' : ?0 }")
    User findByEmailAdress(String emailAdress);
    
    @Query("{ 'nationalId' : ?0 }")
    User findByNationalId(String nationalId);
    
}
