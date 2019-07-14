package me.mskhan.bookmanagement.controller;

import me.mskhan.bookmanagement.entity.Task;
import me.mskhan.bookmanagement.entity.TaskDoneOnly;
import me.mskhan.bookmanagement.exception.TaskNotFoundException;
import me.mskhan.bookmanagement.service.TaskService;
import me.mskhan.bookmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

//    Get all tasks of logged in user
    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskService.findAll();
    }

//    Create a new task
    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task theTask) {
        taskService.save(theTask);
        return theTask;
    }

//    Get task by id
    @GetMapping("/tasks/{taskId}")
    public Task getTasksById(@PathVariable String taskId) {
        return taskService.findById(taskId);
    }

//    Update the task of a user by id
    @PutMapping("/tasks/{taskId}")
    public Task updateTask(@PathVariable String taskId, @RequestBody Task theTask) {
        return taskService.update(theTask, taskId);
    }

//    Delete task by id
    @DeleteMapping("/tasks/{taskId}")
    public String deleteTask(@PathVariable String taskId) {
        Task tempTask = taskService.findById(taskId);

        if (tempTask == null) {
            throw new TaskNotFoundException();
        }

        taskService.delete(taskId);
        return "Deleted task with id "+taskId;
    }

//    Update only done field of Task
    @PatchMapping("/tasks/{taskId}")
    public ResponseEntity<?> partialUpdateDone(@RequestBody TaskDoneOnly partialTask, @PathVariable String taskId) {
        return taskService.partialUpdateDone(partialTask, taskId);
    }
}
