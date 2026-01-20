package anup8bit.com.ecommerce.controller;

import anup8bit.com.ecommerce.model.Order;
import anup8bit.com.ecommerce.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders(
            @RequestParam(required = false) String cursorId,
            @RequestParam(defaultValue = "5") int size
    ) {
        if (cursorId == null) {
            List<Order> orders = orderService.getOrders(size);
            return ResponseEntity.status(HttpStatus.OK).body(orders);
        }

        List<Order> orders = orderService.getOrders(cursorId, size);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable String id) {
        Optional<Order> order = orderService.getOrderDetails(id);
        return order.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
