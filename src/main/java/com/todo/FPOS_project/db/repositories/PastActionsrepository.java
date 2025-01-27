package com.todo.FPOS_project.db.repositories;

import com.todo.FPOS_project.db.models.PastActions;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface PastActionsrepository extends MongoRepository<PastActions, String> {
    
    @Query("{date: '?0'}")
    PastActions findByDate(String date);
    
}
