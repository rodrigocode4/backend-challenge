package br.com.intertrack.backendchallenge.utils;

import br.com.intertrack.backendchallenge.model.Position;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Trip {
    public List<List<Position>> getTrip(List<Position> positions) throws Exception {
        List<List<Position>> trips = new ArrayList<>();

        if (isAllIgnitionsOff(positions) || positions.size() == 0) {
            throw new Exception("Nenhuma Viagem Encontrada");
        }

        if (isAllIgnitionsOn(positions)) {
            List<Position> trip = new ArrayList<>();
            Position startPosition = positions.get(0);
            Position endPosition = positions.get(positions.size() - 1);
            trip.add(startPosition);
            trip.add(endPosition);
            trips.add(trip);
            return trips;
        }

        for (int i = positions.size(); i >= 0 ; i--) {
            int currentIndex = i - 2;
            int previousIndex = i - 1;

            if (currentIndex == -1) {
                Position lastPostion = positions.get(previousIndex);
                List<Position> trip = new ArrayList<>();
                if (lastPostion.getIgnition() == true) {
                    trip.add(lastPostion);
                    trip.add(lastPostion);
                    trips.add(trip);
                }
                break;
            }
            boolean currentIgnition = positions.get(currentIndex).getIgnition();
            boolean previousIgnition = positions.get(previousIndex).getIgnition();

            if (currentIgnition == false && previousIgnition == true) {
                List<Position> lastPositions = new ArrayList<>(positions.subList(previousIndex, positions.size() -1));
                Position lastPosition = positions.get(positions.size() -1);
                lastPositions.add(lastPosition);

                Position start = lastPositions.get(0);
                Position end = lastPositions.get(lastPositions.size() - 1);
                List<Position> trip = new ArrayList<>();
                trip.add(start);
                trip.add(end);
                trips.add(trip);

                positions = positions.subList(0, previousIndex);
            }
        }

        return trips;
    }

    boolean isAllIgnitionsOff(List<Position> positions) {
        long countIgnitions = positions.stream().filter(p -> p.getIgnition() == true).count();
        return countIgnitions == 0 ? true: false;
    }

    boolean isAllIgnitionsOn(List<Position> positions) {
        long countIgnitions = positions.stream().filter(p -> p.getIgnition() == false).count();
        return countIgnitions == 0 ? true: false;
    }
}
