package com.example.SensorRestApp.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementDto {

    @Min(value = -100)
    @Max(value = 100)
    private double value;

    @NotNull
    private boolean raining;

    private SensorDto sensor;

    public MeasurementDto() {

    }

    public MeasurementDto(double value, boolean raining, SensorDto sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDto getSensor() {
        return sensor;
    }

    public void setSensor(SensorDto sensor) {
        this.sensor = sensor;
    }
}
