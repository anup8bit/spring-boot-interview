package anup8bit.com.ecommerce.service.Impl;

import anup8bit.com.ecommerce.model.Order;
import anup8bit.com.ecommerce.repository.OrderRepository;
import anup8bit.com.ecommerce.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Optional<Order> getOrderDetails(String id) {
        return orderRepository.findById(id);
    }

    public List<Order> getOrders(int size) {
        Pageable pageable = PageRequest.of(0, size);
        return orderRepository.findFirstPageOrders(pageable);
    }

    public List<Order> getOrders(String cursorId, int size) {
        Pageable pageable = PageRequest.of(0, size);
        return orderRepository.findNextPageOrders(cursorId, pageable);
    }
}
