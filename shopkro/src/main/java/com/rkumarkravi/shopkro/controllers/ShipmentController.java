package com.rkumarkravi.shopkro.controllers;

import com.rkumarkravi.shopkro.entities.Shipment;
import com.rkumarkravi.shopkro.services.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipment")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @GetMapping("/{orderId}")
    public Shipment getShipmentByOrderId(@PathVariable Long orderId) {
        return shipmentService.getShipmentByOrderId(orderId);
    }

    @PostMapping("/create")
    public Shipment createShipment(@RequestBody Shipment shipment) {
        return shipmentService.createShipment(shipment);
    }

    @PutMapping("/{shipmentId}/update")
    public Shipment updateShipment(@PathVariable Long shipmentId, @RequestBody Shipment updatedShipment) {
        return shipmentService.updateShipment(shipmentId, updatedShipment);
    }

    @DeleteMapping("/{shipmentId}")
    public String deleteShipment(@PathVariable Long shipmentId) {
        shipmentService.deleteShipment(shipmentId);
        return "Shipment deleted successfully!";
    }
}
