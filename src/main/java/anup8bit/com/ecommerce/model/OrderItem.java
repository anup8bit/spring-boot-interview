package anup8bit.com.ecommerce.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_items")
//@IdClass(OrderItemId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

//    @Id
    @Column(name = "order_id", nullable = false)
    private String orderId;

    @Id
    @Column(name = "order_item_id", nullable = false)
    private Integer orderItemId;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "seller_id", nullable = false)
    private String sellerId;

    @Column(name = "shipping_limit_date")
    private LocalDateTime shippingLimitDate;

    @Column(name = "price")
    private Double price;

    @Column(name = "freight_value")
    private Double freightValue;
}

