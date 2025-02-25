package com.example.demo.controller;

import com.example.demo.service.DurationService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class DurationController {

    private final DurationService durationService;    

    public DurationController(DurationService durationService) {
        this.durationService = durationService;
    }

    @GetMapping("/duration/{number}")
    public ResponseEntity<String> processDuration(@PathVariable Integer number) {
        log.info("Received number: {}", number);
        try {
            var result = durationService.processDuration(number);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            log.error("Error processing number: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
} 