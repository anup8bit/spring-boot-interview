package anup8bit.com.ecommerce.service.Impl;

import anup8bit.com.ecommerce.dto.analytics.OrderByStatusDto;
import anup8bit.com.ecommerce.mapper.OrderAnalyticsMapper;
import anup8bit.com.ecommerce.repository.OrderItemRepository;
import anup8bit.com.ecommerce.repository.OrderRepository;
import anup8bit.com.ecommerce.service.AnalyticsService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public AnalyticsServiceImpl(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository
    ) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }
    @Async
    public CompletableFuture<List<OrderByStatusDto>> getOrderByStatus() {
        List<Object[]> rows = orderRepository.findOrderStatusCounts();
        List<OrderByStatusDto> orders = rows.stream()
                .map(OrderAnalyticsMapper.INSTANCE::toDto)
                .toList();
        return CompletableFuture.completedFuture(orders);
    }

    @Async
    public CompletableFuture<BigDecimal> getTotalRevenue() {
        return CompletableFuture.completedFuture(
                orderItemRepository.getTotalRevenue()
        );
    }
}
