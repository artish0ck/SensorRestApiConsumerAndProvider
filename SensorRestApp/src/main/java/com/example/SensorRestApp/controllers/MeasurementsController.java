package com.example.SensorRestApp.controllers;


import com.example.SensorRestApp.dto.MeasurementDto;
import com.example.SensorRestApp.dto.MeasurementsResponse;
import com.example.SensorRestApp.models.Measurement;
import com.example.SensorRestApp.services.MeasurementService;
import com.example.SensorRestApp.util.MeasurementValidator;
import com.example.SensorRestApp.util.SensorErrorResponse;
import com.example.SensorRestApp.util.SensorException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

import static com.example.SensorRestApp.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;

    private final MeasurementService measurementService;

    @Autowired
    public MeasurementsController(ModelMapper modelMapper, MeasurementValidator measurementValidator, MeasurementService measurementService) {
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;

        this.measurementService = measurementService;
    }

    @GetMapping()
    public MeasurementsResponse getMeasurements() {
        return new MeasurementsResponse(measurementService
                .findAll().stream().map(this::convertToMeasurementDto).collect(Collectors.toList()));
    }

    @GetMapping("/rainyDaysCount")
    public Integer getRainyDaysCount() {
        return measurementService.getRainyDaysCount();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> registerMeasurement(@RequestBody @Valid MeasurementDto measurementDto, BindingResult bindingResult) {
        Measurement measurementToAdd = (Measurement) convertToMeasurement(measurementDto);
        measurementValidator.validate(measurementToAdd, bindingResult);
        if (bindingResult.hasErrors()) {
            returnErrorsToClient(bindingResult);
        }
        measurementService.save(convertToMeasurement(measurementDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Measurement convertToMeasurement(MeasurementDto measurementDto) {
        return modelMapper.map(measurementDto, Measurement.class);
    }

    private MeasurementDto convertToMeasurementDto(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDto.class);
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
