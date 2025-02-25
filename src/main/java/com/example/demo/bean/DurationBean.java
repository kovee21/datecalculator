package com.example.demo.bean;

import com.example.demo.service.DurationService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import java.io.Serializable;

@Component
@RequestScope
@Getter
@Setter
@RequiredArgsConstructor
public class DurationBean implements Serializable {
    
    private final DurationService durationService;
    
    private Integer seconds;
    private String result;
    
    public void calculate() {
        result = durationService.processDuration(seconds);
    }
} 