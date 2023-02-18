package Model;

import exception.IncorrectArgumentException;

import java.time.LocalDateTime;

// Ежедневная задача
public class DailyTask extends Task{
    public DailyTask( String title, String description, LocalDateTime timeTask, TaskType type) throws IncorrectArgumentException {
        super( title, description, timeTask, type);
    }

    @Override
    public LocalDateTime getTimeNextType(LocalDateTime dateTime) {
        return dateTime.plusDays(1);
    }
    @Override
    public String toString() {
        return "Задача: " +
                "id = " + getId() +
                ", загаловок = " + getTitle() +
                ", описание = " + getDescription() +
                ", время =" + getTimeTask() +
                ", тип = " + getType();
    }
}
