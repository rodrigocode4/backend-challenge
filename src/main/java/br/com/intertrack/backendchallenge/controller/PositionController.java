package br.com.intertrack.backendchallenge.controller;

import br.com.intertrack.backendchallenge.model.Position;
import br.com.intertrack.backendchallenge.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    @Autowired
    private PositionRepository positionRepository;

    @GetMapping(value = {"", "/"})
    List<Position> getPosition() {
        return positionRepository.findAll();
    }

    @GetMapping("/{vehicleId:[\\d]+}")
    ResponseEntity<List<Position>> getPositionByVehicleId(@PathVariable(value = "vehicleId") Integer vehicleId){
        List<Position> positions = positionRepository.findPositionByVehicleId(vehicleId);
        HttpStatus HttpStatus = validateHttpStatus(positions);
        return new ResponseEntity<>(positions, HttpStatus);
    }

    @GetMapping("/{plate:^[a-zA-Z]{3}-\\d{4}$}")
    ResponseEntity<List<Position>> getPositionByPlate(@PathVariable(value = "plate") String plate) {
        List<Position> positions = positionRepository.findByName(plate.toUpperCase());
        HttpStatus HttpStatus = validateHttpStatus(positions);
        return new ResponseEntity<>(positions, HttpStatus);
    }

    private HttpStatus validateHttpStatus(List<Position> positions) {
        return positions.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK;
    }
}
