package com.rkumarkravi.shopkro.controllers;

import com.rkumarkravi.shopkro.entities.Buyer;
import com.rkumarkravi.shopkro.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buyer")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @GetMapping("/{id}")
    public Buyer getBuyerById(@PathVariable Long id) {
        return buyerService.getBuyerById(id);
    }

    @GetMapping
    public List<Buyer> getAllBuyers() {
        return buyerService.getAllBuyers();
    }

    @PostMapping("/create")
    public Buyer createBuyer(@RequestBody Buyer buyer) {
        return buyerService.createBuyer(buyer);
    }

    @PutMapping("/{id}/update")
    public Buyer updateBuyer(@PathVariable Long id, @RequestBody Buyer buyer) {
        return buyerService.updateBuyer(id, buyer);
    }

    @DeleteMapping("/{id}")
    public String deleteBuyer(@PathVariable Long id) {
        buyerService.deleteBuyer(id);
        return "Buyer deleted successfully!";
    }
}
