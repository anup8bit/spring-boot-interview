package anup8bit.com.ecommerce.dto;

import anup8bit.com.ecommerce.model.Order;
import anup8bit.com.ecommerce.model.Product;
import anup8bit.com.ecommerce.model.Seller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDetailsDto {
    private Order order;      // nested order data
    private Product product;  // nested product data
    private Seller seller;
    private Integer orderItemId;
    private String sellerId;
    private Double price;
    private Double freightValue;
}
