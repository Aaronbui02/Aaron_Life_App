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
        return taskRepository.findByTaskListIdOrderByDateAsc(listId);
    }

    @PostMapping
    public Task createTask(@PathVariable String listId, @RequestBody Task task) {
        TaskList taskList = taskListRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("TaskList not found: " + listId));
        task.setTaskList(taskList);
        return taskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        taskRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable String id, @RequestBody Task updated) {
        Task existing = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found: " + id));
        if (updated.getTitle() != null) {
            existing.setTitle(updated.getTitle());
        }
        if (updated.getDate() != null) {
            existing.setDate(updated.getDate());
        }
        existing.setCompleted(updated.isCompleted());
        return taskRepository.save(existing);
    }
}