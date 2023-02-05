package Model;

import exception.IncorrectArgumentException;

import java.time.LocalDateTime;
import java.util.Objects;

// Задача
public abstract class Task implements Replay{
    private static int idGenerator = 1;
    private int id;
    private String title;
    private String description;
    private LocalDateTime timeTask;
    private TaskType type;

    public Task(String title, String description, LocalDateTime timeTask, TaskType type) throws IncorrectArgumentException {
        this.id = idGenerator++;
        setTitle(title);
        setDescription(description);
        setTimeTask(timeTask);
        setType(type);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) throws IncorrectArgumentException {
        if (title != null && title.isEmpty()){
            this.title = title;
        }else {
            throw new IncorrectArgumentException("Внесите название задачи");
        }
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) throws IncorrectArgumentException {
        if (description != null && title.isEmpty()){
            this.description = description;
        }else {
            throw new IncorrectArgumentException("Внесите описание задачи");
        }
    }

    public LocalDateTime getTimeTask(){
        return timeTask;
    }
    public void setTimeTask(LocalDateTime timeTask) throws IncorrectArgumentException{
        if (timeTask != null){
            this.timeTask = timeTask;
        }else {
            throw new IncorrectArgumentException("Внесите время и дату задачи");
        }
    }

    public TaskType getType() {
        return type;
    }
    public void setType(TaskType type) throws IncorrectArgumentException{
        if (type != null){
            this.type = type;
        }else {
            throw new IncorrectArgumentException("Внесите тип задачи");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(timeTask, task.timeTask) && type == task.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, timeTask, type);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", timeTask=" + timeTask +
                ", type=" + type +
                '}';
    }
}
