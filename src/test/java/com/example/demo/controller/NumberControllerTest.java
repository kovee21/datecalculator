package com.example.demo.controller;

import com.example.demo.service.DurationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DurationControllerTest {

    @Mock
    private DurationService durationService;

    @InjectMocks
    private DurationController durationController;

    @Test
    void whenValidNumber_thenReturnSuccess() {
        var inputSeconds = 3665;
        var expectedResult = "1 hour, 1 minute and 5 seconds";
        when(durationService.processDuration(inputSeconds)).thenReturn(expectedResult);

        var response = durationController.processDuration(inputSeconds);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());

        verify(durationService).processDuration(inputSeconds);
    }

    @Test
    void whenNegativeNumber_thenReturnBadRequest() {
        var inputSeconds = -1;
        var errorMessage = "Only non-negative numbers are allowed";
        when(durationService.processDuration(inputSeconds))
            .thenThrow(new IllegalArgumentException(errorMessage));
        var response = durationController.processDuration(inputSeconds);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());

        verify(durationService).processDuration(inputSeconds);
    }

    @Test
    void whenZeroSeconds_thenReturnSuccess() {
        var inputSeconds = 0;
        var expectedResult = "0 seconds";
        when(durationService.processDuration(inputSeconds)).thenReturn(expectedResult);
        var response = durationController.processDuration(inputSeconds);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());

        verify(durationService).processDuration(inputSeconds);
    }

    @Test
    void whenLargeNumber_thenReturnSuccess() {
        var inputSeconds = Integer.MAX_VALUE;
        var expectedResult = "68 years, 35 days, 3 hours, 14 minutes and 7 seconds";
        when(durationService.processDuration(inputSeconds)).thenReturn(expectedResult);
        var response = durationController.processDuration(inputSeconds);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());

        verify(durationService).processDuration(inputSeconds);
    }

    @Test
    void whenServiceThrowsException_thenReturnBadRequest() {
        var inputSeconds = 1;
        var errorMessage = "Unexpected error";
        when(durationService.processDuration(inputSeconds))
            .thenThrow(new IllegalArgumentException(errorMessage));
        var response = durationController.processDuration(inputSeconds);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());

        verify(durationService).processDuration(inputSeconds);
    }
} 