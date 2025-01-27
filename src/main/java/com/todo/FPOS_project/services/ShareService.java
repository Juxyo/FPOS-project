package com.todo.FPOS_project.services;

import com.todo.FPOS_project.db.models.Share;
import com.todo.FPOS_project.db.repositories.ShareRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareService {
    
    private ShareRepository shareRepository;
    
    public ShareService(ShareRepository shareRepository) {
        this.shareRepository = shareRepository;
    }
    
    public boolean shareExists(String investorId, String propertyId) {
        return shareRepository.findByInvestorAndProperty(investorId, propertyId) != null;
    }
    
    public Share createShare(String investorId, String propertyId, double sharePercent) {
        if (shareExists(investorId, propertyId)) throw new IllegalArgumentException("Share already exists");
        
        Share share = new Share();
        share.setInvestorId(investorId);
        share.setPropertyId(propertyId);
        share.setSharePercent(sharePercent);
        
        return shareRepository.save(share);
    }
    
    public Share updateShare(String investorId, String propertyId, double sharePercent) {
        Share share = shareRepository.findByInvestorAndProperty(investorId, propertyId);
        if (share == null) throw new IllegalArgumentException("Share does not exist");
        
        share.setSharePercent(sharePercent);
        
        return shareRepository.save(share);
    }
    
    public void deleteShare(String investorId, String propertyId) {
        Share share = shareRepository.findByInvestorAndProperty(investorId, propertyId);
        if (share == null) throw new IllegalArgumentException("Share does not exist");
        
        shareRepository.delete(share);
    }
    
    public Share getShare(String investorId, String propertyId) {
        return shareRepository.findByInvestorAndProperty(investorId, propertyId);
    }
    
    public List<Share> getSharesByInvestor(String investorId) {
        return shareRepository.findByInvestorId(investorId);
    }
}
