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
}
