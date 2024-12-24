package com.rkumarkravi.shopkro.services;

import com.rkumarkravi.shopkro.entities.Shipment;
import com.rkumarkravi.shopkro.repositories.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    public Shipment getShipmentByOrderId(Long orderId) {
        return shipmentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Shipment not found for orderId: " + orderId));
    }

    public Shipment createShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    public Shipment updateShipment(Long shipmentId, Shipment updatedShipment) {
        Shipment existingShipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Shipment not found with ID: " + shipmentId));
        existingShipment.setCarrierName(updatedShipment.getCarrierName());
        existingShipment.setTrackingNumber(updatedShipment.getTrackingNumber());
        existingShipment.setStatus(updatedShipment.getStatus());
        existingShipment.setShippedDate(updatedShipment.getShippedDate());
        existingShipment.setExpectedDeliveryDate(updatedShipment.getExpectedDeliveryDate());
        existingShipment.setDeliveredDate(updatedShipment.getDeliveredDate());
        return shipmentRepository.save(existingShipment);
    }

    public void deleteShipment(Long shipmentId) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Shipment not found with ID: " + shipmentId));
        shipmentRepository.delete(shipment);
    }
}
