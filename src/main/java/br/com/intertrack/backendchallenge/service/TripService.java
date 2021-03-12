package br.com.intertrack.backendchallenge.service;

import br.com.intertrack.backendchallenge.model.Position;
import br.com.intertrack.backendchallenge.model.Trip;
import br.com.intertrack.backendchallenge.repository.PositionRepository;
import br.com.intertrack.backendchallenge.utils.TripProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripService {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private TripProcessor tripProcessor;

    public List<Trip> findAll() throws Exception {
        List<Position> positions = positionRepository.findAll();
        if (positions.size() == 0) {
            throw new Exception("Nenhuma posição encontrada do veículo!");
        }
        return tripProcessor
                .getTrips(positions)
                .stream()
                .sorted((o1, o2) -> (int) (o1.getVehicleId() - o2.getVehicleId() & o1.getInitialDateTime() - o2.getInitialDateTime()))
                .collect(Collectors.toList());
    }

    public List<Trip> findByPlate(String plate) throws Exception {
        List<Position> positions = positionRepository.findByName(plate);
        if (positions.size() == 0) {
            throw new Exception("Nenhuma posição encontrada do veículo de placa: " + plate);
        }
        return tripProcessor.getTrips(positions);
    }

    public List<Trip> findByVehicleId(Integer vehicleId) throws Exception {
        List<Position> positions = positionRepository.findPositionByVehicleId(vehicleId);
        if (positions.size() == 0) {
            throw new Exception("Nenhuma posição encontrada do veículo de id: " + vehicleId);
        }
        return tripProcessor.getTrips(positions);
    }
    
}
