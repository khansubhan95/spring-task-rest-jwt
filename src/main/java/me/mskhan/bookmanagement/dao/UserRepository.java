package me.mskhan.bookmanagement.dao;

import me.mskhan.bookmanagement.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    public User findByUsername(String theUsername);
}
