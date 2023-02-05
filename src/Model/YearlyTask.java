package Model;

import exception.IncorrectArgumentException;

import java.time.LocalDateTime;
// Ежегодная задача
public class YearlyTask extends Task{
    public YearlyTask( String title, String description, LocalDateTime timeTask, TaskType type) throws IncorrectArgumentException {
        super( title, description, timeTask, type);
    }

    @Override
    public LocalDateTime getTimeNextType(LocalDateTime dateTime) {
        return dateTime.plusYears(1);
    }
}
