package autoservice.service.mapper.impl;

import autoservice.model.Car;
import autoservice.model.dto.request.CarRequestDto;
import autoservice.model.dto.response.CarResponseDto;
import autoservice.service.CarOwnerService;
import autoservice.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class CarMapper implements Mapper<CarRequestDto, CarResponseDto, Car> {
    private final CarOwnerService carOwnerService;

    public CarMapper(CarOwnerService carOwnerService) {
        this.carOwnerService = carOwnerService;
    }

    @Override
    public CarResponseDto toDto(Car object) {
        CarResponseDto carResponseDto = new CarResponseDto();
        carResponseDto.setId(object.getId());
        carResponseDto.setModel(object.getModel());
        carResponseDto.setBrand(object.getBrand());
        carResponseDto.setYear(object.getYear());
        carResponseDto.setUniqueNumber(object.getUniqueNumber());
        carResponseDto.setCarOwnerId(object.getCarOwner().getId());
        return carResponseDto;
    }

    @Override
    public Car toModel(CarRequestDto dto) {
        Car car = new Car();
        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setYear(dto.getYear());
        car.setUniqueNumber(dto.getUniqueNumber());
        car.setCarOwner(carOwnerService.get(dto.getCarOwnerId()));
        return car;
    }
}
