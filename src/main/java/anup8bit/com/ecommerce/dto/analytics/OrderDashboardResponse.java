package anup8bit.com.ecommerce.dto.analytics;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

//@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDashboardResponse {

    private List<OrderByStatusDto> orders;
    private BigDecimal totalRevenue;

    public OrderDashboardResponse(List<OrderByStatusDto> orders, BigDecimal totalRevenue) {
        this.orders = orders;
        this.totalRevenue = totalRevenue;
    }
}
