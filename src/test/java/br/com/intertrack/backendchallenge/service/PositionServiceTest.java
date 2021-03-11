package br.com.intertrack.backendchallenge.service;

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
public class PositionServiceTest {

    @Autowired
    private PositionService positionService;

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
