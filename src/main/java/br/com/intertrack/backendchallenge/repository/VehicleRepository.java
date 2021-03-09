package br.com.intertrack.backendchallenge.repository;

import br.com.intertrack.backendchallenge.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    Vehicle findByName(String name);
}