package anup8bit.com.ecommerce.controller;

import anup8bit.com.ecommerce.model.User;
import anup8bit.com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {
    private final UserService userService;

    @Value("${app.username}")
    private String username;

    ApplicationContext context;

    public UserController(
            UserService userService,
            ApplicationContext context
    ) {
        this.userService = userService;
        this.context = context;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable String id
    ) {
        Optional<User> user = userService.getUserById(id);
        System.out.println("username : " + username);
        System.out.println(context);
        System.out.println(context.getApplicationName());
        System.out.println(context.getDisplayName());
        System.out.println(context.getAutowireCapableBeanFactory());
        System.out.println(context.getBean("aggregationExecutor"));
        System.out.println(context.getEnvironment());
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
