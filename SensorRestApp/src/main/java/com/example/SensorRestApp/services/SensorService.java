package com.example.SensorRestApp.services;


import com.example.SensorRestApp.Repositories.SensorsRepository;
import com.example.SensorRestApp.models.Sensor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SensorService {

    private final SensorsRepository sensorsRepository;

    public SensorService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    public List<Sensor> findAll() {
        return sensorsRepository.findAll();
    }

    public void save(Sensor sensor) {
        sensorsRepository.save(sensor);
    }

    public Optional<Sensor> findByName(String sensorName) {
        return sensorsRepository.findByName(sensorName);
    }
}
