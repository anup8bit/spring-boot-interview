package anup8bit.com.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="sellers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
    @Id
    private String sellerId;
    private Integer sellerZipCodePrefix;
    private String sellerCity;
    private String sellerState;
}
