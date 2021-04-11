package main.service.impl;

import main.model.User;
import main.repository.UserRepository;
import main.service.UserControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserControllerServiceImpl implements UserControllerService {

    private UserRepository userRepository;

    @Autowired
    public UserControllerServiceImpl(@Qualifier("userRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
