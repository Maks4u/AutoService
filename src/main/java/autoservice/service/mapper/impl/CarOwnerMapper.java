package autoservice.service.mapper.impl;

import autoservice.model.Car;
import autoservice.model.CarOwner;
import autoservice.model.Order;
import autoservice.model.dto.request.CarOwnerRequestDto;
import autoservice.model.dto.response.CarOwnerResponseDto;
import autoservice.service.CarService;
import autoservice.service.OrderService;
import autoservice.service.mapper.Mapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CarOwnerMapper implements Mapper<CarOwnerRequestDto, CarOwnerResponseDto, CarOwner> {
    private final CarService carService;
    private final OrderService orderService;

    public CarOwnerMapper(CarService carService,
                          OrderService orderService) {
        this.carService = carService;
        this.orderService = orderService;
    }

    @Override
    public CarOwnerResponseDto toDto(CarOwner object) {
        CarOwnerResponseDto carOwnerResponseDto = new CarOwnerResponseDto();
        carOwnerResponseDto.setId(object.getId());
        List<Long> carsId = object.getCars().stream().map(Car::getId).collect(Collectors.toList());
        carOwnerResponseDto.setCarsId(carsId);
        List<Long> ordersId = object.getOrders()
                .stream()
                .map(Order::getId)
                .collect(Collectors.toList());
        carOwnerResponseDto.setOrdersId(ordersId);
        return carOwnerResponseDto;
    }

    @Override
    public CarOwner toModel(CarOwnerRequestDto dto) {
        CarOwner carOwner = new CarOwner();
        List<Car> cars = dto.getCarsId().stream().map(carService::get).collect(Collectors.toList());
        carOwner.setCars(cars);
        List<Order> orders = dto.getOrdersId()
                .stream()
                .map(orderService::get)
                .collect(Collectors.toList());
        carOwner.setOrders(orders);
        return carOwner;
    }
}
