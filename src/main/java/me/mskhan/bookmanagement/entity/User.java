package me.mskhan.bookmanagement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document
public class User {
    @Id
    private String id;

    private String username;
    private String password;

//    @DBRef
//    private Set<Task> todos = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Set<Task> getTodos() {
//        return todos;
//    }
//
//    public void setTodos(Set<Task> todos) {
//        this.todos = todos;
//    }
}
