package autoservice.controller;

import autoservice.model.Car;
import autoservice.model.dto.request.CarRequestDto;
import autoservice.model.dto.response.CarResponseDto;
import autoservice.service.CarService;
import autoservice.service.mapper.impl.CarMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final CarMapper carMapper;

    public CarController(CarService carService,
                         CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create Car")
    CarResponseDto save(@RequestBody
                        @Validated
                        @ApiParam(value = "Car parameters") CarRequestDto carRequestDto) {
        Car car = carService.saveOrUpdate(carMapper.toModel(carRequestDto));
        return carMapper.toDto(car);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Car by ID")
    CarResponseDto update(@PathVariable
                          @ApiParam(value = "Car ID") Long id,
                          @RequestBody
                          @Validated
                          @ApiParam(value = "Car parameters") CarRequestDto carRequestDto) {
        Car car = carService.saveOrUpdate(carMapper.toModel(carRequestDto));
        car.setId(id);
        return carMapper.toDto(car);
    }
}
