package com.todo.FPOS_project.controllers;

import com.todo.FPOS_project.db.models.Property;
import com.todo.FPOS_project.dtos.request.BuyShareDTO;
import com.todo.FPOS_project.dtos.request.PropertyCreateDTO;
import com.todo.FPOS_project.dtos.request.PropertyStateDTO;
import com.todo.FPOS_project.dtos.request.PropertyUpdateDTO;
import com.todo.FPOS_project.enums.PropertyState;
import com.todo.FPOS_project.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/property")
@CrossOrigin(origins = "*")
@RestController
public class PropertyController {
    
    @Autowired
    private final PropertyService propertyService;
    
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }
    
    @GetMapping("/{propertyId}")
    public ResponseEntity getProperty(@PathVariable("propertyId") String propertyId) {
        try {
            return ResponseEntity.ok(Map.of("property", propertyService.getProperty(propertyId)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/agent/{agentId}")
    public ResponseEntity getAgentProperties(@PathVariable("agentId") String agentId) {
        try {
            return ResponseEntity.ok(Map.of("properties", propertyService.getPropertiesByAgentId(agentId)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping()
    public ResponseEntity getProperties() {
        try {
            // Performed by Agents
            return ResponseEntity.ok(Map.of("properties", propertyService.getProperties()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/opened")
    public ResponseEntity getOpenedProperties() {
        try {
            return ResponseEntity.ok(Map.of("properties", propertyService.getCatalogue()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
    
    @PostMapping()
    public ResponseEntity createProperty(@RequestBody PropertyCreateDTO propertyCreateDTO) {
        try {
            return ResponseEntity.ok(Map.of("property", propertyService.createProperty(propertyCreateDTO)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
    
    @PutMapping("/{propertyId}")
    public ResponseEntity updateProperty(@PathVariable("propertyId") String propertyId, @RequestBody PropertyUpdateDTO propertyCreateDTO) {
        try {
            return ResponseEntity.ok(Map.of("property", propertyService.updateProperty(propertyId, propertyCreateDTO)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
    
    @DeleteMapping("/{propertyId}")
    public ResponseEntity deleteProperty(@PathVariable("propertyId") String propertyId) {
        try {
            propertyService.deleteProperty(propertyId);
            return ResponseEntity.ok("Deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
    
    @PutMapping("/{propertyId}/state")
    public ResponseEntity changePropertyState(@PathVariable("propertyId") String propertyId, @RequestBody PropertyStateDTO state) {
        try {
            switch (state.getState()) {
                case "OPENED":
                    // Agent owning the property
                    Property property = propertyService.openProperty(propertyId);
                    return ResponseEntity.ok(Map.of("message", "Property opened", "property", property));
                default:
                    return ResponseEntity.badRequest().body(Map.of("message", "Invalid state"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
    
    @PostMapping("/{propertyId}/buy")
    public ResponseEntity buyProperty(@PathVariable("propertyId") String propertyId, @RequestBody BuyShareDTO buyShareDTO) {
        try {
            return ResponseEntity.ok(Map.of("message", "Property bought", "share", propertyService.buyShare(propertyId, buyShareDTO)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}
