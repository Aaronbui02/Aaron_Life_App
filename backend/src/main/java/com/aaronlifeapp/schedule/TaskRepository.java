package com.aaronlifeapp.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findByTaskListId(String taskListId);
    List<Task> findByTaskListIdOrderByDateAsc(String taskListId);
}
