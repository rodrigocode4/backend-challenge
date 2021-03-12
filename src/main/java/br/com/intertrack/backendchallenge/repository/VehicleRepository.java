package br.com.intertrack.backendchallenge.repository;

import br.com.intertrack.backendchallenge.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    List<Vehicle> findAll();

    Vehicle findByName(String name);
}