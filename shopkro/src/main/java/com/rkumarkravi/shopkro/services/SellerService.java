package com.rkumarkravi.shopkro.services;

import com.rkumarkravi.shopkro.entities.Seller;
import com.rkumarkravi.shopkro.repositories.SellerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    public Seller getSellerById(Long id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seller not found with ID: " + id));
    }

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Seller createSeller(Seller seller) {
        System.out.println(seller);
        return sellerRepository.save(seller);
    }

    public Seller updateSeller(Long id, Seller updatedSeller) {
        Seller seller = getSellerById(id);
        seller.setSellerName(updatedSeller.getSellerName());
        seller.setSellerAddress(updatedSeller.getSellerAddress());
        seller.setSellerGst(updatedSeller.getSellerGst());
        return sellerRepository.save(seller);
    }

    public void deleteSeller(Long id) {
        sellerRepository.deleteById(id);
    }
}
