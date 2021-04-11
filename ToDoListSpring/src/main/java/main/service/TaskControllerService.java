package main.service;

import main.model.Task;

import java.util.List;

public interface TaskControllerService {

    void addTask(Task task);

    List<Task> getAllTasks();

    Task getTask(int id);

    void updateTask(Task task);

    void removeTask(int id);
}
