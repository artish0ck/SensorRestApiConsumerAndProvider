package com.example.SensorRestApp.controllers;

import com.example.SensorRestApp.dto.SensorDto;
import com.example.SensorRestApp.models.Sensor;
import com.example.SensorRestApp.services.SensorService;
import com.example.SensorRestApp.util.SensorException;
import com.example.SensorRestApp.util.SensorErrorResponse;
import com.example.SensorRestApp.util.SensorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.SensorRestApp.util.ErrorsUtil.returnErrorsToClient;


@RestController
@RequestMapping("/sensors")
public class SensorsController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;

    private final SensorValidator sensorValidator;

    @Autowired
    public SensorsController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @GetMapping()
    public List<Sensor> getSensors() {
        return sensorService.findAll();
    }


    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDto sensorDto,
                                                     BindingResult bindingResult) {
        Sensor sensorToAdd = (Sensor) convertToSensor(sensorDto);
        sensorValidator.validate(sensorToAdd, bindingResult);
        if (bindingResult.hasErrors()) {
            returnErrorsToClient(bindingResult);
        }
        sensorService.save(convertToSensor(sensorDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Sensor convertToSensor(SensorDto sensorDto) {
        return modelMapper.map(sensorDto, Sensor.class);
    }


    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorException e) {
        SensorErrorResponse response = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );


        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}

