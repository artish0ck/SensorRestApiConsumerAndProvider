package com.example.SensorRestApp.dto;

import java.util.List;

public class MeasurementsResponse {

    private List<MeasurementDto> measurementList;

    public MeasurementsResponse(List<MeasurementDto> measurementList) {
        this.measurementList = measurementList;
    }

    public MeasurementsResponse() {
    }

    public List<MeasurementDto> getMeasurementList() {
        return measurementList;
    }

    public void setMeasurementList(List<MeasurementDto> measurementList) {
        this.measurementList = measurementList;
    }
}
