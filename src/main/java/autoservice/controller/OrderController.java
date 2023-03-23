package autoservice.controller;

import autoservice.model.Order;
import autoservice.model.dto.request.OrderOnlyStatusRequestDto;
import autoservice.model.dto.request.OrderProductsRequestDto;
import autoservice.model.dto.request.OrderRequestDto;
import autoservice.model.dto.response.OrderResponseDto;
import autoservice.model.dto.response.OrderTotalPriceResponseDto;
import autoservice.service.OrderService;
import autoservice.service.mapper.impl.OrderMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService,
                           OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create Order")
    OrderResponseDto save(@RequestBody
                          @Validated
                          @ApiParam(value = "Order parameters")
                          OrderRequestDto orderRequestDto) {
        Order order = orderService.saveOrUpdate(orderMapper.toModel(orderRequestDto));
        return orderMapper.toDto(order);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Order")
    OrderResponseDto update(@PathVariable
                            @ApiParam(value = "Order ID") Long id,
                            @RequestBody
                            @Validated
                            @ApiParam(value = "Order parameters")
                            OrderRequestDto orderRequestDto) {
        Order order = orderService.saveOrUpdate(orderMapper.toModel(orderRequestDto));
        order.setId(id);
        return orderMapper.toDto(order);
    }

    @PostMapping("/{id}")
    @ApiOperation(value = "Add new Products to Order by ID")
    OrderResponseDto addProducts(@PathVariable
                                 @ApiParam(value = "Order ID") Long id,
                                 @RequestBody
                                 @Validated
                                 @ApiParam(value = "List of product ID")
                                 OrderProductsRequestDto products) {
        Order order = orderService.addNewProducts(id, products.getProductsId());
        return orderMapper.toDto(order);
    }

    @PutMapping("/{id}/status")
    @ApiOperation(value = "Change Order status by ID")
    OrderResponseDto changeStatus(@PathVariable
                                  @ApiParam(value = "Order ID") Long id,
                                  @RequestBody
                                  @Validated
                                  @ApiParam(value = "Order status")
                                  OrderOnlyStatusRequestDto status) {
        Order order = orderService.changeStatus(id, status.getStatus());
        return orderMapper.toDto(order);
    }

    @GetMapping("/{id}/price")
    @ApiOperation(value = "Get total price in Order by ID")
    OrderTotalPriceResponseDto getTotalPrice(@PathVariable
                                             @ApiParam(value = "Order ID") Long id) {
        return orderMapper.toDtoPrice(orderService.get(id));
    }
}
