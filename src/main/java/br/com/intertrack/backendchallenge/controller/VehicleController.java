package br.com.intertrack.backendchallenge.controller;

import br.com.intertrack.backendchallenge.model.Vehicle;
import br.com.intertrack.backendchallenge.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping
    List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{name:^[a-zA-Z]{3}-\\d{4}$}")
    Vehicle getVehicleByName(@PathVariable(value = "name") String name) {
        return vehicleService.getVehicleByName(name.toUpperCase());
    }
    
}
