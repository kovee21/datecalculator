package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.utils.DurationUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service    
public class DurationServiceImpl implements DurationService {
    
    private static final String SECOND = "second";
    private static final String MINUTE = "minute";
    private static final String HOUR = "hour";
    private static final String DAY = "day";
    private static final String MONTH = "month";
    private static final String YEAR = "year";
    private static final int MONTH_IN_SECONDS = 2592000; // 30 days
    private static final int YEAR_IN_SECONDS = 31536000; // 365 days

    @Override
    public String processDuration(Integer number) {
        if (number == null || number < 0) {
            throw new IllegalArgumentException("Only positive numbers are allowed!");
        } else if (number == 0) {
            return "now";
        }
        return getDurationString(number);
    }

    private String getDurationString(Integer number) {
        var durations = initializeDurations(number);
              
        var nonZeroDurations = DurationUtil.removeFromListWhenZero(durations);

        var formattedDurations = DurationUtil.joinerWithAnd(nonZeroDurations);

        return formattedDurations;
    }

    private List<String> initializeDurations(Integer number) {
        var years = (int) (number / YEAR_IN_SECONDS);
        var months = (int) (number / MONTH_IN_SECONDS) % 12;
        number = calculateCorrection(number, years, months);       

        var durations = List.of(
            DurationUtil.pluralize(years, YEAR),
            DurationUtil.pluralize(months, MONTH),
            DurationUtil.pluralize((int) (TimeUnit.SECONDS.toDays(number)) % 30, DAY),
            DurationUtil.pluralize((int) (TimeUnit.SECONDS.toHours(number)) % 24, HOUR),
            DurationUtil.pluralize((int) (TimeUnit.SECONDS.toMinutes(number)) % 60, MINUTE),
            DurationUtil.pluralize((int) (TimeUnit.SECONDS.toSeconds(number)) % 60, SECOND)
        );
        return durations;
    }

    private int calculateCorrection(int number, int years, int months) {
        if (years > 0 && months == 0) {            
            number = number - (years * 3600 * 24 * 5); // 5 days/year
        }
        return number;
    }
    
} 