package main.service;

import main.model.User;

public interface UserControllerService {
    public User findByUsername(String username);
    public void saveUser(User user);
}
