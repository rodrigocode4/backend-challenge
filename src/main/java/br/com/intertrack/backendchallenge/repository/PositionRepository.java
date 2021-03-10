package br.com.intertrack.backendchallenge.repository;

import br.com.intertrack.backendchallenge.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Integer> {

    List<Position> findAll();

    List<Position> findPositionByVehicleId(Integer vehicleId);

    @Query(value = "SELECT * FROM POSITION AS P WHERE EXISTS " +
            "(SELECT 1 FROM VEHICLE AS V WHERE P.VEHICLE_ID = V.ID AND V.NAME = :plate)", nativeQuery = true)
    List<Position> findByName(@Param("plate") String plate);
}
