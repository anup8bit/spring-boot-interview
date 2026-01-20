package anup8bit.com.ecommerce.service;

import anup8bit.com.ecommerce.dto.OrderItemDetailsDto;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public interface OrderItemService {
    CompletableFuture<OrderItemDetailsDto> getOrderItemDetails(String id);
}
