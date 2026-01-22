package anup8bit.com.ecommerce.mapper;

import anup8bit.com.ecommerce.dto.PostDto;
import anup8bit.com.ecommerce.dto.PostsResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostResponseMapper {
//    @Mapping(target = "user", source = "userId")
    PostsResponseDto toPostResponseDto(PostDto postDto);
}

