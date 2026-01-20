package anup8bit.com.ecommerce.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemId implements Serializable {
    private String orderId;
    private Integer orderItemId;
}
