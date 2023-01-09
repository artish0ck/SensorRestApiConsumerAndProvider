package com.example.SensorRestApp.util;

import com.example.SensorRestApp.models.Sensor;
import com.example.SensorRestApp.services.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Sensor.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        if(sensorService.findByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name", "Sensor with this name is already registered!");
        }
    }



}
