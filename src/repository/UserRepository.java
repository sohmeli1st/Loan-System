package repository;
import entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository{
    void save(User u);
    List<User> findAll();
    Optional<User> findById(String id);
}
