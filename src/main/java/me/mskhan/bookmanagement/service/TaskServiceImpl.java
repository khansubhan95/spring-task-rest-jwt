package me.mskhan.bookmanagement.service;

import me.mskhan.bookmanagement.dao.TaskRepository;
import me.mskhan.bookmanagement.dao.UserRepository;
import me.mskhan.bookmanagement.entity.Task;
import me.mskhan.bookmanagement.entity.TaskDoneOnly;
import me.mskhan.bookmanagement.entity.User;
import me.mskhan.bookmanagement.exception.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void save(Task theTask) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String theUsername = auth.getName();
        theTask.setOwner(userRepository.findByUsername(theUsername));
        taskRepository.save(theTask);
    }

    @Override
    public List<Task> findAll() {
        String theUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User theUser = userRepository.findByUsername(theUsername);
        return taskRepository.findAllByOwner(theUser);
    }

    @Override
    public Task findById(String theId) {
        Optional<Task> taskOptional = taskRepository.findById(theId);
        Task theTask = null;

        if (taskOptional.isPresent()) {
            theTask = taskOptional.get();
            if (!isAuthenticated(theTask.getOwner().getUsername())) {
                throw new TaskNotFoundException();
            }
        }

        return theTask;
    }

    @Override
    public Task update(Task theTask, String theId) {
        Optional<Task> taskOptional = taskRepository.findById(theId);
        Task originalTask = null;

        if (taskOptional.isPresent()) {
            originalTask = taskOptional.get();
            if (!isAuthenticated(originalTask.getOwner().getUsername())) {
                throw new TaskNotFoundException();
            }
            theTask.setId(theId);
            theTask.setOwner(originalTask.getOwner());
            taskRepository.save(theTask);
            return theTask;
        }

        return null;
    }

    @Override
    public void delete(String theId) {
        Optional<Task> taskOptional = taskRepository.findById(theId);
        Task theTask = null;

        if (taskOptional.isPresent()) {
            theTask = taskOptional.get();
            if (!isAuthenticated(theTask.getOwner().getUsername())) {
                throw new TaskNotFoundException();
            }
            taskRepository.delete(theTask);
        }
    }

    @Override
    public ResponseEntity<?> partialUpdateDone(TaskDoneOnly partialTask, String theId) {
        Optional<Task> taskOptional = taskRepository.findById(theId);
        Task theTask = null;

        if (taskOptional.isPresent()) {
            theTask = taskOptional.get();
            if (!isAuthenticated(theTask.getOwner().getUsername())) {
                throw new TaskNotFoundException();
            }

            theTask.setDone(partialTask.isDone());
            taskRepository.save(theTask);
        }
        return null;
    }


    private boolean isAuthenticated(String theGivenUsername) {
        String theUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        return theUsername.matches(theGivenUsername);
    }

}
