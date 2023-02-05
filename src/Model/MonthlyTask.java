package Model;

import exception.IncorrectArgumentException;

import java.time.LocalDateTime;
// Ежемесячная задача
public class MonthlyTask extends Task{
    public MonthlyTask( String title, String description, LocalDateTime timeTask, TaskType type) throws IncorrectArgumentException {
        super( title, description, timeTask, type);
    }

    @Override
    public LocalDateTime getTimeNextType(LocalDateTime dateTime) {
        return dateTime.plusMonths(1);
    }
}
