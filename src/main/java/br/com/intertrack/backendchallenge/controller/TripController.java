package br.com.intertrack.backendchallenge.controller;

import br.com.intertrack.backendchallenge.model.Position;
import br.com.intertrack.backendchallenge.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    TripService tripService;

    @GetMapping("/{plate:^[a-zA-Z]{3}-\\d{4}$}")
    ResponseEntity<List<List<Position>>> getTrip(@PathVariable(value = "plate") String plate) {
        List<List<Position>> trips = null;
        HttpStatus STATUS = null;

        try {
            trips = tripService.findByPlate(plate.toUpperCase());
            STATUS = HttpStatus.OK;
        } catch (Exception e) {
            trips = new ArrayList<>();
            STATUS = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(trips, STATUS);
    }

    @GetMapping("/{vehicleId:[\\d]+}")
    ResponseEntity<List<List<Position>>> getPositionByVehicleId(@PathVariable(value = "vehicleId") Integer vehicleId){
        List<List<Position>> trips = null;
        HttpStatus STATUS = null;

        try {
            trips = tripService.findByVehicleId(vehicleId);
            STATUS = HttpStatus.OK;
        } catch (Exception e) {
            trips = new ArrayList<>();
            STATUS = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(trips, STATUS);
    }
}
