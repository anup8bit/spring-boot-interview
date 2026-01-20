package anup8bit.com.ecommerce.service;

import anup8bit.com.ecommerce.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> getOrderDetails(String orderId);
    List<Order> getOrders(int size);
    List<Order> getOrders(String cursorId, int size);
}
