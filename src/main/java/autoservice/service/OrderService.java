package autoservice.service;

import autoservice.model.Order;
import java.util.List;

public interface OrderService extends GenericService<Order> {
    List<Order> getAllOrdersByMasterId(Long id);

    List<Order> getAllOrdersByCarOwnerId(Long id);

    Order addNewProducts(Long id, List<Long> products);

    Order changeStatus(Long id, Order.Status status);
}
