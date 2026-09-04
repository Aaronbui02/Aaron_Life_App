package com.aaronlifeapp.schedule;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasklists/{listId}/tasks")
public class TaskController {
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskController(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @GetMapping
    public List<Task> getTasksForList(@PathVariable String listId) {
        return taskRepository.findByTaskListId(listId);
    }

    @PostMapping
    public Task createTask(@PathVariable String listId, @RequestBody Task task) {
        TaskList taskList = taskListRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("TaskList not found: " + listId));
        task.setTaskList(taskList);
        return taskRepository.save(task);
    }
}