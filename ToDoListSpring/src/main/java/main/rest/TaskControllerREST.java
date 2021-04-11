package main.rest;

import main.dto.TaskRequestDTO;
import main.model.Task;
import main.service.ConvertDtoToModel;
import main.service.impl.TaskControllerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskControllerREST
{
    private final TaskControllerServiceImpl taskControllerService;

    @Autowired
    public TaskControllerREST(TaskControllerServiceImpl taskControllerService) {
        this.taskControllerService = taskControllerService;
    }

    //Список всех задач
    @GetMapping("/tasks/")
    public List<Task> list() {
        return taskControllerService.getAllTasks();
    }

    //Добавление задачи
    @PostMapping("/tasks/")
    @ResponseBody
    public void add(TaskRequestDTO taskRequestDTO)
    {
        Task task = ConvertDtoToModel.convertTaskDtoToModel(taskRequestDTO);
        taskControllerService.addTask(task);
    }

    @PostMapping("/tasksExpired/")
    public void addExpiredTask(@RequestBody TaskRequestDTO taskRequestDTO)
    {
        Task task = ConvertDtoToModel.convertTaskDtoToModel(taskRequestDTO);
        taskControllerService.addTask(task);
    }

    @PostMapping("/tasks/{id}")
    public ResponseEntity add(@PathVariable int id) {
        return  ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
    }

    //Статус in progress
    @PutMapping("/taskInProgress/{id}/{status}")
    public void changeStatusToInProgress(@PathVariable int id, @PathVariable String status) {
        changeTaskStatus(id, status);
    }

    //Статус canceled
    @PutMapping("/taskCanceled/{id}/{status}")
    public void changeStatusToCanceled(@PathVariable int id, @PathVariable String status) {
        changeTaskStatus(id, status);
    }

    //Статус closed
    @PutMapping("/taskClosed/{id}/{status}")
    public void changeStatusToClosed(@PathVariable int id, @PathVariable String status) {
        changeTaskStatus(id, status);
    }

    //Статус completed
    @PutMapping("/taskCompleted/{id}/{status}")
    public void changeStatusToCompleted(@PathVariable int id, @PathVariable String status) {
        changeTaskStatus(id, status);
    }

    //Статус deleted
    @PutMapping("/taskDeleted/{id}/{status}")
    public void changeStatusToDeleted(@PathVariable int id, @PathVariable String status) {
        changeTaskStatus(id, status);
    }

    //Удаление всех задач
    @DeleteMapping("/tasks/")
    public void deleteAllTasks() {
        List<Task> tasks = taskControllerService.getAllTasks();
        List<Integer> ids = new ArrayList<>();
        tasks.forEach(t -> ids.add(t.getId()));
        ids.forEach(taskControllerService::removeTask);
    }

    //Полное удаление задачи
    @DeleteMapping("/tasks/{id}")
    public void deleteCurrentTask(@PathVariable int id) {
        taskControllerService.removeTask(id);
    }

    private void changeTaskStatus(int id, String status) {
        if (taskControllerService.getTask(id) != null) {
            Task task = taskControllerService.getTask(id);
            task.setStatus(status);
            taskControllerService.removeTask(id);
            taskControllerService.addTask(task);
        }
    }

}
