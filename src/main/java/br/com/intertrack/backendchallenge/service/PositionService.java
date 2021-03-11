package br.com.intertrack.backendchallenge.service;

import br.com.intertrack.backendchallenge.model.Position;
import br.com.intertrack.backendchallenge.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    public List<Position> findPositionByVehicleId(Integer vehicleId) {
        return positionRepository.findPositionByVehicleId(vehicleId);
    }

    public List<Position> findByName(String plate) throws Exception {
        List<Position> positions = positionRepository.findByName(plate);
        if (positions.size() == 0) {
            throw new Exception("Nenhuma posição encontrada do veículo de placa: " + plate);
        }
        return positions;
    }
}
