package exception;
// Искление если задача не найдена
public class TaskNotFoundException extends Exception{
    public TaskNotFoundException(Integer taskId) {
        super("Задача " + taskId + " не найдена ");
    }
}
