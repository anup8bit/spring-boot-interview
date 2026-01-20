package anup8bit.com.ecommerce.mapper;

import anup8bit.com.ecommerce.dto.analytics.OrderByStatusDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderAnalyticsMapper {
    OrderAnalyticsMapper INSTANCE = Mappers.getMapper(OrderAnalyticsMapper.class);

    // Map raw Object to Dto
    default OrderByStatusDto toDto(Object[] row) {
        return new OrderByStatusDto(
                (String) row[0],
                ((Number) row[1]).longValue()
        );
    }

}
