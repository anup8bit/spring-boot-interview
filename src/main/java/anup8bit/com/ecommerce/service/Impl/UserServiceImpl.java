package anup8bit.com.ecommerce.service.Impl;

import anup8bit.com.ecommerce.model.User;
import anup8bit.com.ecommerce.repository.UserRepository;
import anup8bit.com.ecommerce.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findByCustomerId(userId);
    }

    public List<User> getUsers(String cursor) {
        return userRepository.findAll();
    }

    public List<User> searchUser(String searchText) {
        return userRepository.findAll();
    }



}
