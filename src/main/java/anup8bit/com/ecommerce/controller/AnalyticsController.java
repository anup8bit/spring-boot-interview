package anup8bit.com.ecommerce.controller;

import anup8bit.com.ecommerce.dto.analytics.OrderByStatusDto;
import anup8bit.com.ecommerce.dto.analytics.OrderDashboardResponse;
import anup8bit.com.ecommerce.service.AnalyticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(
        path = "/api/v1/analytics",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    /*
     * 1️⃣ Order Analytics Dashboard API (Fan-Out Aggregation)
     * API
     * GET /api/v1/analytics/orders/summary
     * Parallel aggregations
     * Total orders by order_status
     * GMV (sum of price + freight)
     * Avg delivery time (delivered - approved)
     * Cancelled vs delivered %
     * Orders per day/month
     * Why multi-threading
     * Each aggregation = independent DB query
     * Run in parallel → merge response
     * Threads
     * CompletableFuture.supplyAsync()
     * */
    @GetMapping(path="/orders/summary")
    public CompletableFuture<ResponseEntity<OrderDashboardResponse>> getOrderSummary() {
        CompletableFuture<List<OrderByStatusDto>> ordersByStatus = analyticsService.getOrderByStatus();
        CompletableFuture<BigDecimal> totalRevenue = analyticsService.getTotalRevenue();
        System.out.println(ordersByStatus.isDone());
        return CompletableFuture
                .allOf(ordersByStatus, totalRevenue)
                .thenApply(v ->{
                    List<OrderByStatusDto> orders = ordersByStatus.join();
                    BigDecimal revenue = totalRevenue.join();
                    orders.forEach(order -> {
                        System.out.println(order.getStatus() + " : " + order.getCount());
                    });
                    System.out.println(orders);
                    OrderDashboardResponse resp = new OrderDashboardResponse(orders, revenue);

                    return ResponseEntity.status(HttpStatus.OK).body(resp);
                })
                .exceptionally(ex ->
                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                );
    }
}