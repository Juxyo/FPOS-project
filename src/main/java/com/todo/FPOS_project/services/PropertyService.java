package com.todo.FPOS_project.services;

import com.todo.FPOS_project.db.models.Property;
import com.todo.FPOS_project.db.repositories.PropertyRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {this.propertyRepository = propertyRepository;}

    public boolean propertyExists(String id) {
        return propertyRepository.findByPropertyId(id) != null;
    }

    public Property getProperty(String id) {
        return propertyRepository.findByPropertyId(id);
    }

    public double getEstimatedValueByPropertyId(String id) {
        return propertyRepository.getEstimatedValueByPropertyId(id).getEstimatedValue();
    }
    
    
}
