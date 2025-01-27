package com.todo.FPOS_project.services;

import com.todo.FPOS_project.db.models.Property;
import com.todo.FPOS_project.db.models.Share;
import com.todo.FPOS_project.db.repositories.PropertyRepository;
import com.todo.FPOS_project.db.repositories.ShareRepository;
import com.todo.FPOS_project.dtos.request.BuyShareDTO;
import com.todo.FPOS_project.dtos.request.PropertyCreateDTO;
import com.todo.FPOS_project.dtos.request.PropertyUpdateDTO;
import com.todo.FPOS_project.dtos.response.CatalogueResponseDTO;
import com.todo.FPOS_project.enums.PropertyState;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserService userService;
    private final ShareService shareService;
    private final TransactionService transactionService;
    private final ShareRepository shareRepository;
    
    private List<String> lockedForPurchase = new ArrayList<>();

    public PropertyService(PropertyRepository propertyRepository, UserService userService, ShareService shareService, TransactionService transactionService, ShareRepository shareRepository) {
        this.propertyRepository = propertyRepository;
        this.userService = userService;
        this.shareService = shareService;
        this.transactionService = transactionService;
        this.shareRepository = shareRepository;
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
        return propertyRepository.findByState(state.name());
    }
    
    public double getTotalShareBought(String propertyId) {
        double totalShare = 0;

        for (Share share : shareRepository.findByPropertyId(propertyId)) {
            totalShare += share.getSharePercent();
        }
        
        return totalShare;
    }
    
    public List<CatalogueResponseDTO> getCatalogue() {
        List<Property> properties = propertyRepository.findByState(PropertyState.OPENED.name());
        List<CatalogueResponseDTO> catalogue = new ArrayList<>();
        
        for (Property property : properties) {
            catalogue.add(new CatalogueResponseDTO(property, property.getEstimatedValue() * (100/(100-getTotalShareBought(property.getId())))));
        }
        
        return catalogue;
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
    
    public Property openProperty(String id) {
        if (!propertyExists(id)) throw new IllegalArgumentException("Property does not exist.");
        
        Property property = getProperty(id);
        
        if (propertyRepository.findByStateAndAgentId(PropertyState.OPENED.name(), property.getAgentId()).size() >= 6) throw new IllegalArgumentException("Agent has reached the maximum number of opened properties.");
        
        if (property.getState() != PropertyState.SAVED && property.getState() != PropertyState.CLOSED) throw new IllegalArgumentException("Property is not in a state that can be opened.");
        
        property.setState(PropertyState.OPENED);
        property.setLastFoundingStartDate(LocalDate.now());
        
        return propertyRepository.save(property);
    }
    
    public Share buyShare(String propertyId, BuyShareDTO buyShareDTO) {
        if (!userService.userIdExists(buyShareDTO.getInvestorId())) throw new IllegalArgumentException("Investor does not exist.");
        if (!userService.isUserIdEnabled(buyShareDTO.getInvestorId())) throw new IllegalArgumentException("Investor is disabled.");
        if (!userService.isUserInvestor(buyShareDTO.getInvestorId())) throw new IllegalArgumentException("Investor is an agent.");
        
        if (!propertyExists(propertyId)) throw new IllegalArgumentException("Property does not exist.");
        Property property = getProperty(propertyId);
        if (property.getState() != PropertyState.OPENED) throw new IllegalArgumentException("Property is not in a state that can be bought.");
        
        if (buyShareDTO.getAmount() < 500) throw new IllegalArgumentException("Amount must be greater than 500EUR.");
        if (transactionService.getTotalBuyAmountLastYear(buyShareDTO.getInvestorId()) + buyShareDTO.getAmount() > 100000) throw new IllegalArgumentException("Investor has reached the maximum amount of 10000EUR for a year.");
        if (property.getEstimatedValue() * (100/(100-getTotalShareBought(property.getId()))) < buyShareDTO.getAmount()) throw new IllegalArgumentException("Amount is greater than the remaining value of the property.");
        
        
        if (lockedForPurchase.contains(propertyId)) {
            try {
                Thread.sleep(500);
                return buyShare(propertyId, buyShareDTO);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            lockedForPurchase.add(propertyId);
        }
        
        transactionService.createBuyTransaction(buyShareDTO.getInvestorId(), propertyId, buyShareDTO.getAmount());
        
        if (shareService.shareExists(propertyId, buyShareDTO.getInvestorId())) {
            Share share = shareService.updateShare(buyShareDTO.getInvestorId(), propertyId, shareService.getShare(buyShareDTO.getInvestorId(), propertyId).getSharePercent() + buyShareDTO.getAmount()/property.getEstimatedValue());
            lockedForPurchase.remove(propertyId);
            return share;
        }
        
        Share share = shareService.createShare(buyShareDTO.getInvestorId(), propertyId, buyShareDTO.getAmount()/property.getEstimatedValue());
        lockedForPurchase.remove(propertyId);
        return share;
    }
}
