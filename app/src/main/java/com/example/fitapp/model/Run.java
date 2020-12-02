package com.example.fitapp.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Run {

    private float distance;
    private float pace;
    private String dateTime;
    private String time;

    public Run(float distance, float pace) {
        this.distance = distance;
        this.pace = pace;

    }
}
