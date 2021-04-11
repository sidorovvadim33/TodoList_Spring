package main.service.impl;

import main.model.Task;
import main.repository.TaskRepository;
import main.service.TaskControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskControllerServiceImpl implements TaskControllerService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskControllerServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void addTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : taskIterable) {
            tasks.add(task);
        }
        return tasks;
    }

    @Override
    public Task getTask(int id) {
        return taskRepository.findById(id).isPresent() ? taskRepository.findById(id).get() : null;
    }

    @Override
    public void updateTask(Task task) {
        if (taskRepository.findById(task.getId()).isPresent()) {
            taskRepository.save(task);
        }
    }

    @Override
    public void removeTask(int id) {
        taskRepository.deleteById(id);
    }
}
