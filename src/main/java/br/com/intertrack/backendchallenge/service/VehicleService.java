package br.com.intertrack.backendchallenge.service;

import br.com.intertrack.backendchallenge.model.Vehicle;
import br.com.intertrack.backendchallenge.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleByName(String name) {
        return vehicleRepository.findByName(name);
    }
}
