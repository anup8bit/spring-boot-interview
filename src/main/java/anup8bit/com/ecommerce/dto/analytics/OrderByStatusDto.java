package anup8bit.com.ecommerce.dto.analytics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class OrderByStatusDto {
    private String status;
    private Long count;

    // Manually add this to OrderByStatusDto.java if Lombok fails
    public OrderByStatusDto(String status, Long count) {
        this.status = status;
        this.count = count;
    }

}
