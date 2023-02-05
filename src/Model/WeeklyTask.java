package Model;

import exception.IncorrectArgumentException;

import java.time.LocalDateTime;
// Еженедельная задача
public class WeeklyTask extends Task{
    public WeeklyTask( String title, String description, LocalDateTime timeTask, TaskType type) throws IncorrectArgumentException {
        super( title, description, timeTask, type);
    }

    @Override
    public LocalDateTime getTimeNextType(LocalDateTime dateTime) {
        return dateTime.plusWeeks(1);
    }
}
