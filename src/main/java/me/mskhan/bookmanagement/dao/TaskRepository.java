package me.mskhan.bookmanagement.dao;

import me.mskhan.bookmanagement.entity.Task;
import me.mskhan.bookmanagement.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    public List<Task> findAllByOwner(User theUser);
}
