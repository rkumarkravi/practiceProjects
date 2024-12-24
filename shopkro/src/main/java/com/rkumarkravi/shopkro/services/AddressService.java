package com.rkumarkravi.shopkro.services;

import com.rkumarkravi.shopkro.entities.Address;
import com.rkumarkravi.shopkro.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with ID: " + id));
    }

    public List<Address> getAddressesByBuyerId(Long buyerId) {
        return addressRepository.findByBuyerId(buyerId);
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address updateAddress(Long id, Address updatedAddress) {
        Address address = getAddressById(id);
        address.setLocality(updatedAddress.getLocality());
        address.setCity(updatedAddress.getCity());
        address.setPinCode(updatedAddress.getPinCode());
        return addressRepository.save(address);
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}

