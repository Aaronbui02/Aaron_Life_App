package com.aaronlifeapp.schedule;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasklists")
public class TaskListController {
    private final TaskListRepository taskListRepository;

    public TaskListController(TaskListRepository taskListRepository) {
        this.taskListRepository= taskListRepository;
    }

    @GetMapping
    public List<TaskList> getAllTaskLists() {
        return taskListRepository.findAll();
    }

    @PostMapping
    public TaskList createTaskList(@RequestBody TaskList tl) {
        return taskListRepository.save(tl);
    }

    @DeleteMapping("/{id}")
    public void deleteTaskList(@PathVariable String id) {
        taskListRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public TaskList updateTaskList(@PathVariable String id, @RequestBody TaskList updated) {
        TaskList existing = taskListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TaskList not found: " + id));
        if (updated.getTitle() != null) {
            existing.setTitle(updated.getTitle());
        }
        return taskListRepository.save(existing);
    }
}
