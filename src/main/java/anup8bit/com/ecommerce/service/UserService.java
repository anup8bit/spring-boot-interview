package anup8bit.com.ecommerce.service;

import anup8bit.com.ecommerce.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(String id);
    List<User> getUsers(String cursor);
    List<User> searchUser(String searchText);
}
