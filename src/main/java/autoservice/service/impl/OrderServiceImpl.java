package autoservice.service.impl;

import autoservice.model.Order;
import autoservice.model.Product;
import autoservice.repository.OrderRepository;
import autoservice.service.MoneyManager;
import autoservice.service.OrderService;
import autoservice.service.ProductService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final MoneyManager moneyManager;
    private final ProductService productService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            MoneyManager moneyManager,
                            ProductService productService) {
        this.orderRepository = orderRepository;
        this.moneyManager = moneyManager;
        this.productService = productService;
    }

    @Override
    public Order saveOrUpdate(Order entity) {
        BigDecimal bigDecimal = moneyManager.calculatePrice(entity);
        entity.setTotalPrice(bigDecimal);
        return orderRepository.save(entity);
    }

    @Override
    public Order get(Long id) {
        return orderRepository.getById(id);
    }

    @Override
    public List<Order> getAllOrdersByMasterId(Long id) {
        return orderRepository.getOrdersByWorksMasterId(id);
    }

    @Override
    public List<Order> getAllOrdersByCarOwnerId(Long id) {
        return orderRepository.getOrdersByCarCarOwnerId(id);
    }

    @Override
    public Order addNewProducts(Long id, List<Long> products) {
        Order order = orderRepository.getById(id);
        List<Product> newProductsList = products
                .stream()
                .map(productService::get)
                .collect(Collectors.toList());
        order.setProducts(newProductsList);
        BigDecimal price = moneyManager.calculatePrice(order);
        order.setTotalPrice(price);
        return orderRepository.save(order);
    }

    @Override
    public Order changeStatus(Long id, Order.Status status) {
        Order order = orderRepository.getById(id);
        order.setStatus(status);
        if (order.getStatus().equals(Order.Status.DONE)
                || order.getStatus().equals(Order.Status.DONE_FAIL)) {
            order.setEndDate(LocalDate.now());
            return orderRepository.save(order);
        }
        return orderRepository.save(order);
    }
}
