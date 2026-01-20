package anup8bit.com.ecommerce.controller;

import anup8bit.com.ecommerce.client.PostClientService;
import anup8bit.com.ecommerce.dto.PostsResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/posts")
public class PostController {
    private final PostClientService postClientService;

    public PostController(PostClientService postClientService) {
        this.postClientService = postClientService;
    }

    @GetMapping
    public ResponseEntity<List<PostsResponseDto>> getPosts() {
        List<PostsResponseDto> posts = postClientService.getPosts();

        return ResponseEntity.ok(posts);
    }
}
