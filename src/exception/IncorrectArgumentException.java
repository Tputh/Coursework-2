package exception;
// Исключение в случае не коректных данных задачи
public class IncorrectArgumentException extends Exception{
    private final String argument;

    public IncorrectArgumentException(String argument) {
        this.argument = argument;
    }

    public String getMessage() {
        return " нет параметра " + argument;
    }
}
