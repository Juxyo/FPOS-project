package com.todo.FPOS_project.services;

import com.todo.FPOS_project.db.models.Property;
import com.todo.FPOS_project.db.repositories.PropertyRepository;
import com.todo.FPOS_project.dtos.request.PropertyCreateDTO;
import com.todo.FPOS_project.dtos.request.PropertyUpdateDTO;
import com.todo.FPOS_project.enums.PropertyState;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserService userService;

    public PropertyService(PropertyRepository propertyRepository, UserService userService) {
        this.propertyRepository = propertyRepository;
        this.userService = userService;
    }

    public boolean propertyExists(String id) {
        return propertyRepository.findByPropertyId(id) != null;
    }

    public Property getProperty(String id) {
        Property property = propertyRepository.findByPropertyId(id);
        if (property == null) throw new IllegalArgumentException("Property does not exist.");
        
        return property;
    }
    
    public List<Property> getProperties() {
        return propertyRepository.findAll();
    }
    
    public List<Property> getPropertiesByState(PropertyState state) {
        return propertyRepository.findByStatus(state.name());
    }
    
    public List<Property> getPropertiesByAgentId(String agentId) {
        if (!userService.userIdExists(agentId)) throw new IllegalArgumentException("Agent does not exist.");
        if (!userService.isUserIdEnabled(agentId)) throw new IllegalArgumentException("Agent is disabled.");
        if (userService.isUserInvestor(agentId)) throw new IllegalArgumentException("Agent is an investor.");
        
        return propertyRepository.findByAgentId(agentId);
    }
    
    public Property createProperty(PropertyCreateDTO propertyCreateDTO) {
        if (!userService.userIdExists(propertyCreateDTO.getAgentId())) throw new IllegalArgumentException("Agent does not exist.");
        if (!userService.isUserIdEnabled(propertyCreateDTO.getAgentId())) throw new IllegalArgumentException("Agent is disabled.");
        if (userService.isUserInvestor(propertyCreateDTO.getAgentId())) throw new IllegalArgumentException("Agent is an investor.");
        
        Property property = new Property(
                propertyCreateDTO.getName(),
                propertyCreateDTO.getAgentId(),
                propertyCreateDTO.getAddress(),
                propertyCreateDTO.getEstimatedValue(),
                propertyCreateDTO.getAnnualRentIncomePercent(),
                propertyCreateDTO.getAnnualAppreciationPercent(),
                propertyCreateDTO.getType()
        );
        
        property.setState(PropertyState.SAVED);
        property.setCreationDate(LocalDate.now());
        
        return propertyRepository.save(property);
    }
    
    public Property updateProperty(String propertyId, PropertyUpdateDTO propertyUpdateDTO) {
        if (!propertyUpdateDTO.getAgentId().isPresent()) throw new IllegalArgumentException("Agent ID is required.");
        
        if (!userService.userIdExists(propertyUpdateDTO.getAgentId().get())) throw new IllegalArgumentException("Agent does not exist.");
        if (!userService.isUserIdEnabled(propertyUpdateDTO.getAgentId().get())) throw new IllegalArgumentException("Agent is disabled.");
        if (userService.isUserInvestor(propertyUpdateDTO.getAgentId().get())) throw new IllegalArgumentException("Agent is an investor.");
        
        if (!propertyExists(propertyId)) throw new IllegalArgumentException("Property does not exist.");

        Property property = propertyRepository.findByPropertyId(propertyId);
        
        if (property.getState() != PropertyState.SAVED) throw new IllegalArgumentException("Property is not in a state that can be updated.");
        
        property.setName(propertyUpdateDTO.getName().isPresent() ? propertyUpdateDTO.getName().get() : property.getName());
        property.setAgentId(propertyUpdateDTO.getAgentId().get());
        property.setAddress(propertyUpdateDTO.getAddress().isPresent() ? propertyUpdateDTO.getAddress().get() : property.getAddress());
        property.setEstimatedValue(propertyUpdateDTO.getEstimatedValue().isPresent() ? propertyUpdateDTO.getEstimatedValue().getAsDouble() : property.getEstimatedValue());
        property.setAnnualRentIncomePercent(propertyUpdateDTO.getAnnualRentIncomePercent().isPresent() ? propertyUpdateDTO.getAnnualRentIncomePercent().getAsDouble() : property.getAnnualRentIncomePercent());
        property.setAnnualAppreciationPercent(propertyUpdateDTO.getAnnualAppreciationPercent().isPresent() ? propertyUpdateDTO.getAnnualAppreciationPercent().getAsDouble() : property.getAnnualAppreciationPercent());
        property.setType(propertyUpdateDTO.getType().isPresent() ? propertyUpdateDTO.getType().get() : property.getType());
        
        return propertyRepository.save(property);
    }
    
    public void deleteProperty(String id) {
        if (!propertyExists(id)) throw new IllegalArgumentException("Property does not exist.");
        
        if (getProperty(id).getState() != PropertyState.SAVED) throw new IllegalArgumentException("Property is not in a state that can be deleted.");
        
        propertyRepository.deleteById(id);
    }
    
    public Property openProperty(String id, String agentId) {
        if (!propertyExists(id)) throw new IllegalArgumentException("Property does not exist.");
        
        Property property = getProperty(id);
        
        if (property.getState() != PropertyState.SAVED && property.getState() != PropertyState.CLOSED) throw new IllegalArgumentException("Property is not in a state that can be opened.");
        
        property.setState(PropertyState.OPENED);
        
        return propertyRepository.save(property);
    }
}
