package com.example.SensorRestApp.Repositories;

import com.example.SensorRestApp.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorsRepository extends JpaRepository<Sensor, String> {

    public Optional<Sensor> findByName(String name);
}
