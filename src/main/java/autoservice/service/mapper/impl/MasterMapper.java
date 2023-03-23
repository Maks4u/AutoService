package autoservice.service.mapper.impl;

import autoservice.model.Master;
import autoservice.model.Order;
import autoservice.model.dto.request.MasterRequestDto;
import autoservice.model.dto.response.MasterResponseDto;
import autoservice.model.dto.response.MasterSalaryResponseDto;
import autoservice.service.OrderService;
import autoservice.service.mapper.Mapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class MasterMapper implements Mapper<MasterRequestDto, MasterResponseDto, Master> {
    private final OrderService orderService;

    public MasterMapper(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public MasterResponseDto toDto(Master object) {
        MasterResponseDto masterResponseDto = new MasterResponseDto();
        masterResponseDto.setId(object.getId());
        masterResponseDto.setName(object.getName());
        List<Long> ordersId = object.getDoneOrders()
                .stream()
                .map(Order::getId)
                .collect(Collectors.toList());
        masterResponseDto.setDoneOrdersId(ordersId);
        return masterResponseDto;
    }

    @Override
    public Master toModel(MasterRequestDto dto) {
        Master master = new Master();
        master.setName(dto.getName());
        List<Order> orders = dto.getDoneOrdersId()
                .stream()
                .map(orderService::get)
                .collect(Collectors.toList());
        master.setDoneOrders(orders);
        return master;
    }

    public MasterSalaryResponseDto toDtoSalary(BigDecimal bigDecimal) {
        MasterSalaryResponseDto dto = new MasterSalaryResponseDto();
        dto.setSalary(bigDecimal);
        return dto;
    }
}
