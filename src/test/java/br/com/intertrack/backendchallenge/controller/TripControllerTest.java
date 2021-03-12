package br.com.intertrack.backendchallenge.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TripControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TripController tripController;


    @Test
    @DisplayName("Deve retornar todas as viagens")
    void test_DeveRetornarTodasAsViagens() throws Exception {
        mockMvc.perform(get("/api/trips")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(6)));
    }


    @Test
    @DisplayName("Dado Path De Placa Vehicle1, Retornar 3 Viagens")
    void test_DadoPathDeVehicle1_Retornar3Viagens() throws Exception {
        String plate = "MTX-8901";
        mockMvc.perform(get("/api/trips/"+ plate)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$", hasSize(3))));
    }

    @Test
    @DisplayName("Dado Path De Placa Vehicle1, Retornar 3 Viagens Com VehicleId1")
    void test_DadoPathDePlacaVehicle1_Retornar3ViagensComVehicleId1() throws Exception {
        String plate = "MTX-8901";
        Integer vehicleId = 1;
        mockMvc.perform(get("/api/trips/"+ plate)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$[0].vehicleId", is(vehicleId))))
                .andExpect((jsonPath("$[1].vehicleId", is(vehicleId))))
                .andExpect((jsonPath("$[2].vehicleId", is(vehicleId))));
    }

    @Test
    @DisplayName("Dado Path De VehicleId1, Retornar 3 Viagens Com VehicleId1")
    void test_DadoPathDeVehicleId1_Retornar3ViagensComVehicleId1() throws Exception {
        Integer vehicleId = 1;
        mockMvc.perform(get("/api/trips/"+ vehicleId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$[0].vehicleId", is(vehicleId))))
                .andExpect((jsonPath("$[1].vehicleId", is(vehicleId))))
                .andExpect((jsonPath("$[2].vehicleId", is(vehicleId))));
    }

    @Test
    @DisplayName("Dado Path De VehicleId3, Não retonar nenhuma Viagem")
    void test_DadoPathDePlacaVehicle3_NaoRetonarNenhuamViagem() throws Exception {
        String plate = "ABH-2303";
        mockMvc.perform(get("/api/trips/"+ plate)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect((jsonPath("$", hasSize(0))));
    }

    @Test
    @DisplayName("Deve retonar um array vazio")
    void test_DadoPathDeVehicleId3_RetornarArrayVazio() throws Exception {
        Integer vehicleId = 3;
        mockMvc.perform(get("/api/trips/"+ vehicleId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect((jsonPath("$", hasSize(0))));
    }

    @Test
    @DisplayName("Deve retonar um array vazio")
    void test_DadoPathDeVehicleIdInexistente_RetornarArrayVazio() throws Exception {
        Integer vehicleId = 1000;
        mockMvc.perform(get("/api/trips/"+ vehicleId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect((jsonPath("$", hasSize(0))));
    }
}


