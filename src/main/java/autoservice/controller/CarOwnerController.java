package autoservice.controller;

import autoservice.model.CarOwner;
import autoservice.model.Order;
import autoservice.model.dto.request.CarOwnerRequestDto;
import autoservice.model.dto.response.CarOwnerResponseDto;
import autoservice.model.dto.response.OrderResponseDto;
import autoservice.service.CarOwnerService;
import autoservice.service.OrderService;
import autoservice.service.mapper.impl.CarOwnerMapper;
import autoservice.service.mapper.impl.OrderMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owners")
public class CarOwnerController {
    private final CarOwnerService carOwnerService;
    private final CarOwnerMapper carOwnerMapper;
    private final OrderMapper orderMapper;
    private final OrderService orderService;

    public CarOwnerController(CarOwnerService carOwnerService,
                              CarOwnerMapper carOwnerMapper,
                              OrderMapper orderMapper,
                              OrderService orderService) {
        this.carOwnerService = carOwnerService;
        this.carOwnerMapper = carOwnerMapper;
        this.orderMapper = orderMapper;
        this.orderService = orderService;
    }

    @PostMapping
    @ApiOperation(value = "Create CarOwner")
    CarOwnerResponseDto save(@RequestBody
                             @Validated
                             @ApiParam(value = "CarOwner parameters")
                             CarOwnerRequestDto carOwnerRequestDto) {
        CarOwner carOwner = carOwnerService.saveOrUpdate(carOwnerMapper
                .toModel(carOwnerRequestDto));
        return carOwnerMapper.toDto(carOwner);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update CarOwner")
    CarOwnerResponseDto update(@PathVariable
                               @ApiParam(value = "CarOwner ID") Long id,
                               @RequestBody
                               @Validated
                               @ApiParam(value = "CarOwner parameters")
                               CarOwnerRequestDto carOwnerRequestDto) {
        CarOwner carOwner = carOwnerService.saveOrUpdate(carOwnerMapper
                .toModel(carOwnerRequestDto));
        carOwner.setId(id);
        return carOwnerMapper.toDto(carOwner);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get all CarOwner's orders by ID")
    List<OrderResponseDto> getAllOrders(@PathVariable
                                        @ApiParam(value = "CarOwner ID") Long id) {
        List<Order> orders = orderService.getAllOrdersByCarOwnerId(id);
        return orders.stream().map(orderMapper::toDto).collect(Collectors.toList());
    }
}
