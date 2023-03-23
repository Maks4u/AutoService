package autoservice.controller;

import autoservice.model.Master;
import autoservice.model.Order;
import autoservice.model.dto.request.MasterRequestDto;
import autoservice.model.dto.response.MasterResponseDto;
import autoservice.model.dto.response.MasterSalaryResponseDto;
import autoservice.model.dto.response.OrderResponseDto;
import autoservice.service.MasterService;
import autoservice.service.OrderService;
import autoservice.service.mapper.impl.MasterMapper;
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
@RequestMapping("/masters")
public class MasterController {
    private final MasterService masterService;
    private final MasterMapper masterMapper;
    private final OrderMapper orderMapper;
    private final OrderService orderService;

    public MasterController(MasterService masterService,
                            MasterMapper masterMapper,
                            OrderMapper orderMapper,
                            OrderService orderService) {
        this.masterService = masterService;
        this.masterMapper = masterMapper;
        this.orderMapper = orderMapper;
        this.orderService = orderService;
    }

    @PostMapping
    @ApiOperation(value = "Create Master")
    MasterResponseDto save(@RequestBody
                           @Validated
                           @ApiParam(value = "Master parameters")
                           MasterRequestDto masterRequestDto) {
        Master master = masterService.saveOrUpdate(masterMapper.toModel(masterRequestDto));
        return masterMapper.toDto(master);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Master")
    MasterResponseDto update(@PathVariable
                             @ApiParam(value = "Master ID") Long id,
                             @RequestBody
                             @Validated
                             @ApiParam(value = "Master parameters")
                             MasterRequestDto masterRequestDto) {
        Master master = masterService.saveOrUpdate(masterMapper.toModel(masterRequestDto));
        master.setId(id);
        return masterMapper.toDto(master);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get all Master's orders by ID")
    List<OrderResponseDto> getAllOrders(@PathVariable
                                        @ApiParam(value = "Master ID") Long id) {
        List<Order> orders = orderService.getAllOrdersByMasterId(id);
        return orders.stream().map(orderMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}/salary")
    @ApiOperation(value = "Pay salary to Master by ID")
    MasterSalaryResponseDto giveSalary(@PathVariable
                                       @ApiParam(value = "Master ID") Long id) {
        return masterMapper.toDtoSalary(masterService.getSalary(id));
    }
}
