package Model;

import exception.IncorrectArgumentException;

import java.time.LocalDateTime;

// Единоразовая задача
public class OneTimeTask extends Task{

    public OneTimeTask( String title, String description, LocalDateTime timeTask, TaskType type) throws IncorrectArgumentException {
        super( title, description, timeTask, type);
    }



    @Override
    public LocalDateTime getTimeNextType(LocalDateTime dateTime) {
        return null;
    }
}
