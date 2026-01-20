package anup8bit.com.ecommerce.repository;

import anup8bit.com.ecommerce.dto.analytics.OrderByStatusDto;
import anup8bit.com.ecommerce.model.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findOrderByCustomerId(String customerId);
    List<Order> findOrderByOrderStatus(String status);

    // first page data
    @Query("""
        SELECT o FROM Order o
        order by o.orderId desc
        limit 5
    """)
    List<Order> findFirstPageOrders(
            Pageable pageable
    );


    @Query("""
        select o from Order o
            where o.orderId < :id
            order by o.orderId desc
            limit 5
    """)
    List<Order> findNextPageOrders(
            @Param("id") String id,
            Pageable pageable
    );

    @Query("""
    SELECT o.orderStatus AS orderStatus,
           COUNT(o) AS totalOrders
    FROM Order o
    GROUP BY o.orderStatus
""")
    List<Object[]> findOrderStatusCounts();

}
