package com.example.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DurationServiceImplTest {

    private DurationService durationService;

    @BeforeEach
    void setUp() {
        durationService = new DurationServiceImpl();
    }

    @Test
    void whenSecondsIsZero_thenReturnNow() {
        var result = durationService.processDuration(0);
        assertEquals("now", result);
    }

    @Test
    void whenSecondsIsOne_thenReturnOneSecond() {
        var result = durationService.processDuration(1);
        assertEquals("1 second", result);
    }

    @Test
    void whenSecondsIsNegative_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> 
            durationService.processDuration(-1)
        );
    }

    @Test
    void whenOneMinute_thenReturnCorrectFormat() {
        var result = durationService.processDuration(60);
        assertEquals("1 minute", result);
    }

    @Test
    void whenOneHour_thenReturnCorrectFormat() {
        var result = durationService.processDuration(3600);
        assertEquals("1 hour", result);
    }

    @Test
    void whenOneDay_thenReturnCorrectFormat() {
        var result = durationService.processDuration(86400);
        assertEquals("1 day", result);
    }

    @Test
    void whenOneYear_thenReturnCorrectFormat() {
        var result = durationService.processDuration(31536000);
        assertEquals("1 year", result);
    }

    @Test
    void whenComplexDuration_thenReturnCorrectFormat() {
        // 1 year, 0 months, 2 days, 3 hours, 4 minutes and 5 seconds
        int seconds = 31536000 + 172800 + 10800 + 240 + 5;
        var result = durationService.processDuration(seconds);
        assertEquals("1 year, 2 days, 3 hours, 4 minutes and 5 seconds", result);
    }

    @Test
    void whenDurationWithZeroComponents_thenSkipZeros() {
        // 1 year, 0 months, 0 days, 1 hour, 0 minutes and 1 second
        int seconds = 31536000 + 3600 + 1;
        var result = durationService.processDuration(seconds);
        assertEquals("1 year, 1 hour and 1 second", result);
    }

    @Test
    void whenMaxIntegerValue_thenProcessCorrectly() {
        var result = durationService.processDuration(Integer.MAX_VALUE);
        assertTrue(result.contains("years"));
        assertFalse(result.contains("0 seconds"));
    }
} 