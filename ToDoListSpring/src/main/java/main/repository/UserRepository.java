package main.repository;

import main.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}