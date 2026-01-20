package anup8bit.com.ecommerce.controller;

import anup8bit.com.ecommerce.dto.OrderItemDetailsDto;
import anup8bit.com.ecommerce.service.OrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path="/api/v1/order-item")
public class OrderItemController {
    private final OrderItemService orderItemService;

    public OrderItemController(
            OrderItemService orderItemService
    ) {
        this.orderItemService = orderItemService;
    }

    @GetMapping(path="/{orderItemId}/details")
    public CompletableFuture<ResponseEntity<OrderItemDetailsDto>> getOrderItemDetails(
            @PathVariable String orderItemId
    ) {
        CompletableFuture<OrderItemDetailsDto> orderItemDetailsDtoFuture =
                orderItemService.getOrderItemDetails(orderItemId);

        return orderItemDetailsDtoFuture
                .thenApply(ResponseEntity::ok) // wrap successful result
                .exceptionally(ex -> {
                    // log error if needed
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }
}
