package com.example.SensorRestApp.services;

import com.example.SensorRestApp.Repositories.MeasurementsRepository;
import com.example.SensorRestApp.models.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementsRepository measurementsRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementsRepository measurementsRepository, SensorService sensorService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorService = sensorService;
    }

    public List<Measurement> findAll() {
        List<Measurement> measurementList = measurementsRepository.findAll();
        return measurementList;
    }

    @Transactional(readOnly = false)
    public void save(Measurement measurement) {
        enrich(measurement);
        measurementsRepository.save(measurement);
    }

    public Integer getRainyDaysCount() {
        return Math.toIntExact(measurementsRepository.findAll().stream().filter(Measurement::isRaining).count());
    }

    private void enrich(Measurement measurement) {
        measurement.setMeasuredAt(LocalDateTime.now());
    }
}
