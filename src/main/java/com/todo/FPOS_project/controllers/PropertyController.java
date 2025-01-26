package com.todo.FPOS_project.controllers;

import com.todo.FPOS_project.dtos.request.PropertyCreateDTO;
import com.todo.FPOS_project.dtos.request.PropertyUpdateDTO;
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
    
    @GetMapping()
    public ResponseEntity getProperty(@RequestParam String propertyId) {
        try {
            return ResponseEntity.ok(Map.of("property", propertyService.getProperty(propertyId)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/properties")
    public ResponseEntity getProperties(@RequestParam String agentId) {
        try {
            return ResponseEntity.ok(Map.of("property", propertyService.getPropertiesByAgentId(agentId)));
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
    
    @PutMapping()
    public ResponseEntity updateProperty(@RequestBody PropertyUpdateDTO propertyCreateDTO) {
        try {
            return ResponseEntity.ok(Map.of("property", propertyService.updateProperty(propertyCreateDTO)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
    
    @DeleteMapping()
    public ResponseEntity deleteProperty(@RequestParam String propertyId) {
        try {
            return ResponseEntity.ok("Deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
    
}
