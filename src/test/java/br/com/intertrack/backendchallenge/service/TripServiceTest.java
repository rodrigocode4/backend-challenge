package br.com.intertrack.backendchallenge.service;

import br.com.intertrack.backendchallenge.model.Position;
import br.com.intertrack.backendchallenge.model.Trip;
import br.com.intertrack.backendchallenge.utils.TripProcessor;
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
public class TripServiceTest {

    @Autowired
    TripService tripService;

    @Autowired
    TripProcessor trip;

    @Test
    @DisplayName("Deve retornar 3 viagens")
    void test_Dado_Vehicle1_Retornar_3Viagens() throws Exception {
        List<Trip> trips = tripService.findByPlate("MTX-8901");

        int expected = 3;
        int actual = trips.size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Deve retornar 2 viagens")
    void test_Dado_Vehicle2_Retornar_2Viagens() throws Exception {
        List<Trip> trips = tripService.findByPlate("ZDF-5602");

        int expected = 2;
        int actual = trips.size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Deve disparar exceção de Nenhuma viagem encontrada")
    void test_Dado_Vehicle3_NaoRetornarNenhumaViagem() throws Exception {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            List<Trip> trips = tripService.findByPlate("ABH-2303");
        });

        String expectedMessage = "Nenhuma Viagem Encontrada";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Retonar uma viagem")
    void test_Dado_Vehicle4_Retornar_1Viagem() throws Exception {
        List<Trip> trips = tripService.findByPlate("BRT-3204");

        int expected = 1;
        int actual = trips.size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Deve disparar exceção de Nenhuma Posição Encontrado")
    void testDadoAPlacaDoVeiculo5_DispararExcecaoDeNaoEncontrado() {
        String plate = "UTR-2105";
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            List<Trip> trips = tripService.findByPlate(plate);
        });

        String expectedMessage = "Nenhuma posição encontrada do veículo de placa: " + plate;
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

}
