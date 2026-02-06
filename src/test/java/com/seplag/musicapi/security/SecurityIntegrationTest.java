package com.seplag.musicapi.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveRetornar403SemToken() throws Exception {
        mockMvc.perform(get("/api/v1/artistas"))
                .andExpect(status().isForbidden());
    }

    @Test
    void devePermitirLoginComCredenciaisValidas() throws Exception {
        String body = """
        {
          "username": "admin",
          "password": "123456"
        }
    """;

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType("application/json")
                        .accept("application/json")
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }
}
