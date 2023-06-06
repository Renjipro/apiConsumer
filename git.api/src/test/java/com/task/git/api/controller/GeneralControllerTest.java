package com.task.git.api.controller;

import com.task.git.api.model.ErrorResponseBody;
import com.task.git.api.service.GeneralService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class GeneralControllerTest {
    private GeneralService generalService;
    private GeneralController generalController;

    @BeforeEach
    void setUp() {
        generalService = Mockito.mock(GeneralService.class);
        generalController = new GeneralController(generalService);
    }

    @Test
    void shouldReturnRepositories() throws IOException, InterruptedException {

        ResponseEntity expected = ResponseEntity.ok("repositories");
        when(generalService.getRepositoryResponseEntity(any(String.class))).thenReturn(expected);

        ResponseEntity<?> response = generalController.getRepositories("application/json", "username");

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnIncorrectHeaderValue() throws IOException, InterruptedException {
        ResponseEntity expected = ResponseEntity.status(HttpStatusCode.valueOf(406)).body(ErrorResponseBody.builder().status("406").message("Incorrect header value").build());
        when(generalController.getRepositories("Accept", "username")).thenReturn(expected);

        ResponseEntity<?> response = generalController.getRepositories("application/xml", "username");

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnNotExistedName() throws IOException, InterruptedException {
        ResponseEntity expected = ResponseEntity.status(HttpStatusCode.valueOf(404)).body(ErrorResponseBody.builder().status("404").message("Given user does not exists").build());

        when(generalService.getRepositoryResponseEntity("renjipro2")).thenReturn(expected);

        ResponseEntity<?> response = generalService.getRepositoryResponseEntity("renjipro2");

        assertEquals(expected, response);
    }
}