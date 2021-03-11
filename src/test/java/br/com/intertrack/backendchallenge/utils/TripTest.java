package br.com.intertrack.backendchallenge.utils;

import br.com.intertrack.backendchallenge.model.Position;
import br.com.intertrack.backendchallenge.service.PositionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TripTest {

    private List<Position> positions;

    @Autowired
    private Trip trip;

    @Autowired
    private PositionService positionService;


    @Test
    @DisplayName("Deve retornar 3 viagens")
    void test_Dado_Vehicle1_Retornar_3Viagens() throws Exception {
        positions = positionService.findByName("MTX-8901");
        List<List<Position>> trips = trip.getTrip(positions);

        int expected = 3;
        int actual = trips.size();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    @DisplayName("Deve retornar a ignição true e false")
    void test_Dado_Vehicle1_DeveRetornar_AIgnicaoDaPrimeiraViagemVerdadeiraEFalsa() throws Exception {
        positions = positionService.findByName("MTX-8901");
        List<List<Position>> trips = trip.getTrip(positions);

        boolean startIgnition = trips.get(1).get(0).getIgnition();
        boolean endIngnition = trips.get(1).get(1).getIgnition();

        Assertions.assertTrue(startIgnition);
        Assertions.assertFalse(endIngnition);

    }
    @Test
    @DisplayName("Deve retornar a ignição true e true")
    void test_Dado_Vehicle1_DeveRetornar_AIgnicaoDaPrimeiraViagemVerdadeiraEVerdadeira() throws Exception {
        positions = positionService.findByName("MTX-8901");
        List<List<Position>> trips = trip.getTrip(positions);

        boolean startIgnition = trips.get(0).get(0).getIgnition();
        boolean endIngnition = trips.get(0).get(1).getIgnition();

        boolean startIgnition1 = trips.get(2).get(0).getIgnition();
        boolean endIngnition1 = trips.get(2).get(1).getIgnition();

        Assertions.assertTrue(startIgnition);
        Assertions.assertTrue(endIngnition);
        Assertions.assertTrue(startIgnition1);
        Assertions.assertTrue(endIngnition1);

    }

    @Test
    @DisplayName("Deve retornar 2 viagens")
    void test_Dado_Vehicle2_Retornar_2Viagens() throws Exception {
        positions = positionService.findByName("ZDF-5602");
        List<List<Position>> trips = trip.getTrip(positions);
        int expected = 2;
        int actual = trips.size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Deve disparar exceção de Nenhuma viagem encontrada")
    void test_Dado_Vehicle3_NaoRetornarNenhumaViagem() throws Exception {
        positions = positionService.findByName("ABH-2303");
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            List<List<Position>> trips = trip.getTrip(positions);
        });

        String expectedMessage = "Nenhuma Viagem Encontrada";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Retonar uma viagem")
    void test_Dado_Vehicle4_Retornar_1Viagem() throws Exception {
        positions = positionService.findByName("BRT-3204");
        List<List<Position>> trips = trip.getTrip(positions);

        int expected = 1;
        int actual = trips.size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Deve disparar exceção de Nenhuma Posição Encontrado")
    void testDadoAPlacaDoVeiculo5_DispararExcecaoDeNaoEncontrado() {
        String plate = "UTR-2105";
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            List<Position> positions = positionService.findByName(plate);
        });

        String expectedMessage = "Nenhuma posição encontrada do veículo de placa: " + plate;
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
