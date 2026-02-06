package com.seplag.musicapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ArtistaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveRetornar403AoListarArtistasSemToken() throws Exception {
        mockMvc.perform(get("/api/v1/artistas"))
                .andExpect(status().isForbidden());
    }
}

