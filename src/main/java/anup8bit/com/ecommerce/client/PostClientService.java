package anup8bit.com.ecommerce.client;

import anup8bit.com.ecommerce.dto.PostDto;
import anup8bit.com.ecommerce.dto.PostsResponseDto;
import anup8bit.com.ecommerce.dto.UserDto;
import anup8bit.com.ecommerce.mapper.PostResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
public class PostClientService {
    private final RestTemplate restTemplate;
    private final PostResponseMapper postResponseMapper;
    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final Executor executor;
    private final String USER_API_BASE_URL = "https://jsonplaceholder.typicode.com/users";

    @Autowired
    public PostClientService(
            RestTemplate restTemplate,
            PostResponseMapper postResponseMapper,
            @Qualifier("aggregationExecutor") Executor executor
            ) {
        this.restTemplate = restTemplate;
        this.postResponseMapper = postResponseMapper;
        this.executor = executor;
    }

    public List<PostsResponseDto> getPosts() {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .path("/posts")
                .queryParam("limit", 10)
                .toUriString();

        PostDto[] posts = restTemplate.getForObject(url, PostDto[].class);

        List<CompletableFuture<PostsResponseDto>> postResponseDtoFutures = Arrays.stream(posts)
                .map(post ->
                        CompletableFuture.supplyAsync(() -> {
                            return restTemplate.getForObject(USER_API_BASE_URL + "/" + post.getUserId(), UserDto.class);
                            }, executor)
                                .thenApply(userDto -> {
                                    PostsResponseDto postsResponseDto = postResponseMapper.toPostResponseDto(post);
                                    postsResponseDto.setUser(userDto);
                                    return postsResponseDto;
                                })
                ).toList();

        return postResponseDtoFutures.stream()
                .map(CompletableFuture::join)
                .toList();
    }
}
