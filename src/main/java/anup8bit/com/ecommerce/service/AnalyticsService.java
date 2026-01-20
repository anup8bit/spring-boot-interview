package anup8bit.com.ecommerce.service;

import anup8bit.com.ecommerce.dto.analytics.OrderByStatusDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AnalyticsService {
    CompletableFuture<List<OrderByStatusDto>> getOrderByStatus();
    CompletableFuture<BigDecimal> getTotalRevenue();
}
