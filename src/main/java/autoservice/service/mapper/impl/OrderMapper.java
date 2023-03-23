package autoservice.service.mapper.impl;

import autoservice.model.Order;
import autoservice.model.Product;
import autoservice.model.Work;
import autoservice.model.dto.request.OrderRequestDto;
import autoservice.model.dto.response.OrderResponseDto;
import autoservice.model.dto.response.OrderTotalPriceResponseDto;
import autoservice.service.CarService;
import autoservice.service.ProductService;
import autoservice.service.WorkService;
import autoservice.service.mapper.Mapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper implements Mapper<OrderRequestDto, OrderResponseDto, Order> {
    private final CarService carService;
    private final WorkService workService;
    private final ProductService productService;

    public OrderMapper(CarService carService,
                       WorkService workService,
                       ProductService productService) {
        this.carService = carService;
        this.workService = workService;
        this.productService = productService;
    }

    @Override
    public OrderResponseDto toDto(Order object) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(object.getId());
        orderResponseDto.setCarId(object.getCar().getId());
        orderResponseDto.setDescription(object.getDescription());
        orderResponseDto.setStatus(object.getStatus());
        orderResponseDto.setEndDate(object.getEndDate());
        orderResponseDto.setTotalPrice(object.getTotalPrice());
        orderResponseDto.setTakenDate(object.getTakenDate());
        List<Long> productsId = object.getProducts()
                .stream()
                .map(Product::getId)
                .collect(Collectors.toList());
        orderResponseDto.setProductsId(productsId);
        List<Long> servicesId = object.getWorks()
                .stream()
                .map(Work::getId)
                .collect(Collectors.toList());
        orderResponseDto.setServicesId(servicesId);
        return orderResponseDto;
    }

    @Override
    public Order toModel(OrderRequestDto dto) {
        Order order = new Order();
        order.setDescription(dto.getDescription());
        order.setStatus(dto.getStatus());
        order.setEndDate(dto.getEndDate());
        order.setTakenDate(dto.getTakenDate());
        order.setCar(carService.get(dto.getCarId()));
        List<Product> products = dto.getProducts()
                .stream()
                .map(productService::get)
                .collect(Collectors.toList());
        order.setProducts(products);
        List<Work> works = dto.getServices()
                .stream()
                .map(workService::get)
                .collect(Collectors.toList());
        order.setWorks(works);
        return order;
    }

    public OrderTotalPriceResponseDto toDtoPrice(Order order) {
        OrderTotalPriceResponseDto dto = new OrderTotalPriceResponseDto();
        dto.setTotalPrice(order.getTotalPrice());
        return dto;
    }
}
