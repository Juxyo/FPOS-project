package com.todo.FPOS_project.services;

import com.todo.FPOS_project.db.models.Property;
import com.todo.FPOS_project.db.repositories.PropertyRepository;
import com.todo.FPOS_project.enums.PropertyState;
import com.todo.FPOS_project.enums.PropertyType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {this.propertyRepository = propertyRepository;}

    public void createProperty() {
        Property property = new Property();
        propertyRepository.save(property);
    }

    public void createProperty(String name, String agentId, String address, double estimatedValue, double annualRentIncomePercent, double annualAppreciationPercent, LocalDate creationDate, LocalDate lastFoundingStartDate, PropertyState state, PropertyType type) {
        Property property = new Property(name, agentId, address, estimatedValue, annualRentIncomePercent, annualAppreciationPercent, creationDate, lastFoundingStartDate, state, type);
        propertyRepository.save(property);
    }

    public void deleteProperty(String id) {
        propertyRepository.deleteById(id);
    }

    public boolean PropertyExists(String id) {return propertyRepository.findByPropertyId(id) != null;}

    public Property getProperty(String id) {return propertyRepository.findByPropertyId(id);}

    public double getEstimatedValueByPropertyId(String id) {return propertyRepository.getEstimatedValueByPropertyId(id).getEstimatedValue();}
}
