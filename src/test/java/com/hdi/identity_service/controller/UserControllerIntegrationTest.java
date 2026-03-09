package com.hdi.identity_service.controller;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hdi.identity_service.dto.request.UserCreationRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/test.properties")
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private UserCreationRequest request;
    private LocalDate dob;

    @BeforeEach
    void initData() {
        dob = LocalDate.of(1990, 1, 1);

        request = UserCreationRequest.builder()
                .username("john1")
                .firstName("John1")
                .lastName("Doe")
                .password("12345678")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String content = objectMapper.writeValueAsString(request);

        //        mockMvc.perform(MockMvcRequestBuilders.post("/users")
        //                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        //                        .content(content))
        //                .andExpect(MockMvcResultMatchers.status().isOk())
        //                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1000));
        //                .andExpect(MockMvcResultMatchers.jsonPath("result.username").value("john1"));
    }
}
