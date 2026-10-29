package repository;

import entity.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository{
    List<User> users = new ArrayList<>();
    Map<String, User> usersMap = new HashMap<>();

    @Override
    public void save(User u){
        users.add(u);
        usersMap.put(u.getUserId(), u);
    }

    @Override
    public List<User> findAll(){
        for(User u : users){
            System.out.println(u);
        }
        return users;
    }

    @Override
    public Optional<User> findById(String id){
        return Optional.ofNullable(usersMap.get(id));
    }
}