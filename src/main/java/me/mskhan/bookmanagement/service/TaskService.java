package me.mskhan.bookmanagement.service;

import me.mskhan.bookmanagement.entity.Task;
import me.mskhan.bookmanagement.entity.TaskDoneOnly;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface TaskService {
    public void save(Task theTask);
    public List<Task> findAll();
    public Task findById(String theId);
    public Task update(Task theTask, String theId);
    public void delete(String theId);
    public ResponseEntity<?> partialUpdateDone(TaskDoneOnly partialTask, String theId);
}
