package anup8bit.com.ecommerce.repository;

import anup8bit.com.ecommerce.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, String> {
    @Query("""
        SELECT COALESCE(SUM(oi.price + oi.freightValue), 0)
        FROM OrderItem oi
    """)
    BigDecimal getTotalRevenue();

    Optional<OrderItem> getOrderItemByOrderId(String orderId);
}

