package com.ecommerce.app.service;

import com.ecommerce.app.domain.User;
import com.ecommerce.app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public void createUser(User user){
        this.userRepository.save(user);
    }
}
