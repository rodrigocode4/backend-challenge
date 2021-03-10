package br.com.intertrack.backendchallenge.resources;

import br.com.intertrack.backendchallenge.model.Vehicle;
import br.com.intertrack.backendchallenge.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleResource {

    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping(value = {"", "/"})
    List<Vehicle> getVehicle() {
        return vehicleRepository.findAll();
    }


}
