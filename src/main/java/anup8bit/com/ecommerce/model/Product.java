package anup8bit.com.ecommerce.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @Column(name = "product_id")
    private String productId;

    private String productCategoryName;
    private Integer productWeightG;
    private Integer productLengthCm;
    private Integer productHeightCm;
    private Integer productWidthCm;
    private Double price;
}
