package service;

import Model.Task;
import exception.TaskNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
// Сервис по работе с задачами

public class ServiceTask {
    private final Map<Integer, Task> taskMap = new HashMap<>();

    public void add(Task task) {
        this.taskMap.put(task.getId(), task);
    }

    public void remove(Integer taskId) throws TaskNotFoundException {
        if (this.taskMap.containsKey(taskId)) {
            this.taskMap.remove(taskId);
        } else {
            throw new TaskNotFoundException(taskId);
        }
    }

    public Collection<Task> getAllByDate(LocalDate date) {
        Collection<Task> tasksByDay = new ArrayList<>();
        for (Task task : taskMap.values()) {
            LocalDateTime timeTask = task.getTimeTask();
            LocalDateTime timeNextType = task.getTimeNextType(timeTask);
            if (timeNextType == null || timeTask.toLocalDate().equals(date)) {
                tasksByDay.add(task);
                continue;
            }
            do {
                if (timeNextType.toLocalDate().equals(date)) {
                    tasksByDay.add(task);
                    break;
                }
                timeNextType = task.getTimeNextType(timeNextType);
            } while (timeNextType.toLocalDate().isBefore(date));
        }
        return tasksByDay;
    }
}
