package br.com.intertrack.backendchallenge.service;

import br.com.intertrack.backendchallenge.model.Position;
import br.com.intertrack.backendchallenge.repository.PositionRepository;
import br.com.intertrack.backendchallenge.utils.TripProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private TripProcessor tripProcessor;

    public List<List<Position>> findByPlate(String plate) throws Exception {
        List<Position> positions = positionRepository.findByName(plate);
        if (positions.size() == 0) {
            throw new Exception("Nenhuma posição encontrada do veículo de placa: " + plate);
        }
        return tripProcessor.getTrips(positions);
    }

    public List<List<Position>> findByVehicleId(Integer vehicleId) throws Exception {
        List<Position> positions = positionRepository.findPositionByVehicleId(vehicleId);
        if (positions.size() == 0) {
            throw new Exception("Nenhuma posição encontrada do veículo de id: " + vehicleId);
        }
        return tripProcessor.getTrips(positions);
    }
    
}
