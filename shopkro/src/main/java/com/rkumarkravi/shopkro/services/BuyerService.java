package com.rkumarkravi.shopkro.services;

import com.rkumarkravi.shopkro.entities.Buyer;
import com.rkumarkravi.shopkro.repositories.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerService implements UserDetailsService {
    @Autowired
    private BuyerRepository buyerRepository;

    public Buyer getBuyerById(Long id) {
        return buyerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Buyer not found with ID: " + id));
    }

    public List<Buyer> getAllBuyers() {
        return buyerRepository.findAll();
    }

    public Buyer createBuyer(Buyer buyer) {
        return buyerRepository.save(buyer);
    }

    public Buyer updateBuyer(Long id, Buyer updatedBuyer) {
        Buyer buyer = getBuyerById(id);
        buyer.setBuyerName(updatedBuyer.getBuyerName());
        buyer.setEmail(updatedBuyer.getEmail());
        buyer.setGender(updatedBuyer.getGender());
        buyer.setMobNo(updatedBuyer.getMobNo());
        return buyerRepository.save(buyer);
    }

    public void deleteBuyer(Long id) {
        buyerRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        return buyerRepository.findById(Long.parseLong(username)).get();
    }
}

