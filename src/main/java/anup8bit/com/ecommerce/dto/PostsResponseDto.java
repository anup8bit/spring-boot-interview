package anup8bit.com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsResponseDto {
    private Integer id;
    private UserDto user;
    private String title;
    private String body;
}
