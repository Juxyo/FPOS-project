package com.todo.FPOS_project.controllers;

import com.todo.FPOS_project.db.models.Share;
import com.todo.FPOS_project.services.PropertyService;
import com.todo.FPOS_project.services.ShareService;
import com.todo.FPOS_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.Map;

@RequestMapping("/share")
@CrossOrigin(origins = "*")
@RestController
public class ShareController {
    
    @Autowired
    private final ShareService shareService;
    
    @Autowired
    private final PropertyService propertyService;
    
    @Autowired
    private final UserService userService;
    
    public ShareController(ShareService shareService, PropertyService propertyService, UserService userService) {
        this.shareService = shareService;
        this.propertyService = propertyService;
        this.userService = userService;
    }
    
    @GetMapping()
    public ResponseEntity getShare(@RequestParam String propertyId, @RequestParam String investorId) {
        if (!userService.userIdExists(investorId)) return ResponseEntity.badRequest().body("Investor does not exist");
        if (!userService.isUserIdEnabled(investorId)) return ResponseEntity.badRequest().body("Investor is not enabled");
        if (!userService.isUserInvestor(investorId)) return ResponseEntity.badRequest().body("User is not an investor");
        
        if (!propertyService.propertyExists(propertyId)) return ResponseEntity.badRequest().body("Property does not exist");
        
        if (!shareService.shareExists(propertyId, investorId)) return ResponseEntity.badRequest().body("Share does not exist");
        
        try {
            Share share = shareService.getShare(propertyId, investorId);

            return ResponseEntity.ok(Map.of("share", share));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
    
    @GetMapping("/shares")
    public ResponseEntity getShares(@RequestParam String investorId) {
        if (!userService.userIdExists(investorId)) return ResponseEntity.badRequest().body("Investor does not exist");
        if (!userService.isUserIdEnabled(investorId)) return ResponseEntity.badRequest().body("Investor is not enabled");
        if (!userService.isUserInvestor(investorId)) return ResponseEntity.badRequest().body("User is not an investor");
        
        try {
            return ResponseEntity.ok(Map.of("shares", shareService.getSharesByInvestor(investorId)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}
