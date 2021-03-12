package br.com.intertrack.backendchallenge.utils;

import br.com.intertrack.backendchallenge.model.Position;
import br.com.intertrack.backendchallenge.model.Trip;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class TripProcessor {
    public List<Trip> getTrips(List<Position> positions) throws Exception {
        List<Trip> listTrip = new ArrayList<>();

        if (isAllIgnitionsOff(positions) || positions.size() == 0) {
            throw new Exception("Nenhuma Viagem Encontrada");
        }

        if (isAllIgnitionsOn(positions)) {
            Trip trip = getTripBetween(positions.get(0), positions.get(positions.size() - 1));
            listTrip.add(trip);
            return listTrip;
        }

        for (int i = positions.size(); i >= 0 ; i--) {
            int currentIndex = i - 2;
            int previousIndex = i - 1;

            if (currentIndex == -1) {
                Position lastPostion = positions.get(previousIndex);
                if (lastPostion.getIgnition() == false) break;
                Trip trip = getTripOnePosition(lastPostion);
                listTrip.add(trip);
                break;
            }

            boolean currentIgnition = positions.get(currentIndex).getIgnition();
            boolean previousIgnition = positions.get(previousIndex).getIgnition();

            if (currentIgnition == false && previousIgnition == true) {
                List<Position> lastPositions = new ArrayList<>(positions.subList(previousIndex, positions.size() -1));
                Position lastPosition = positions.get(positions.size() -1);
                lastPositions.add(lastPosition);

                Trip trip = getTripBetween(lastPositions.get(0), lastPositions.get(lastPositions.size() - 1));
                listTrip.add(trip);

                positions = positions.subList(0, previousIndex);
            }
        }

        return listTrip;
    }

    boolean isAllIgnitionsOff(List<Position> positions) {
        long countIgnitions = positions.stream().filter(p -> p.getIgnition() == true).count();
        return countIgnitions == 0 ? true: false;
    }

    boolean isAllIgnitionsOn(List<Position> positions) {
        long countIgnitions = positions.stream().filter(p -> p.getIgnition() == false).count();
        return countIgnitions == 0 ? true: false;
    }

    Trip getTripBetween(Position startPosition,  Position endPosition) {

        Integer distanceInMeters = calcDistanceInMetersBetween(startPosition, endPosition);

        Long diffMillisSeconds = Math.abs(endPosition.getDatetime() - startPosition.getDatetime());
        Integer totalTimeInMinutes = Math.toIntExact(TimeUnit.MILLISECONDS.toMinutes(diffMillisSeconds));

        return buildTrip(
                startPosition.getVehicleId(),
                startPosition.getDatetime(),
                endPosition.getDatetime(),
                startPosition.getAddress(),
                endPosition.getAddress(),
                distanceInMeters,
                totalTimeInMinutes
        );
    }

    Trip getTripOnePosition(Position lastPostion) {
        Integer totalTimeInMinutes = 0;
        Integer distanceInMeters = 0;

        return buildTrip(lastPostion.getVehicleId(),
                lastPostion.getDatetime(),
                lastPostion.getDatetime(),
                lastPostion.getAddress(),
                lastPostion.getAddress(),
                distanceInMeters,
                totalTimeInMinutes);
    }

    Trip buildTrip(Integer vehicleId, Long initialDateTime, Long finalDateTime, String initialAddress, String finalAddress, Integer distanceInMeters, Integer totalTimeInMinutes) {
        return new Trip(vehicleId, initialDateTime, finalDateTime, initialAddress, finalAddress, distanceInMeters, totalTimeInMinutes);
    }

    Integer calcDistanceInMetersBetween(Position startPosition,  Position endPosition) {
        return endPosition.getHodometro() - startPosition.getHodometro();
    }
}
