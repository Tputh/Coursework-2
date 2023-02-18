import Model.*;
import exception.IncorrectArgumentException;
import exception.TaskNotFoundException;
import service.TaskService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final TaskService taskService = new TaskService();
    private static final Pattern DATE_TIME_PATTERN =  Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}\\:\\d{2}");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm");
    private static final Pattern DATE_PATTERN =  Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static void inputTask(Scanner scanner) {
        scanner.useDelimiter("\n");

        String title = inputTaskTitle(scanner);
        String description = inputTaskDescription(scanner);
        TaskType type = inputTaskType(scanner);
        LocalDateTime timeTask = inputTaskTime(scanner);
        int repeatability = inputRepeatability(scanner);

        createTask(title, description, type, timeTask, repeatability);
    }


    private static String inputTaskTitle(Scanner scanner) {
        System.out.println("Выведите название задачи");
        String title = scanner.next();
        if (title.isBlank()) {
            System.out.println("Необходимо ввест название задачи");
            scanner.close();
        }
        return title;
    }

    private static String inputTaskDescription(Scanner scanner) {
        System.out.println("Выведите описание задачи");
        String description = scanner.next();
        if (description.isBlank()) {
            System.out.println("Необходимо ввест описание задачи");
            scanner.close();
        }
        return description;
    }

    private static TaskType inputTaskType(Scanner scanner) {
        System.out.println("Выведите тип задачи (1 - личная, 2 - рабочая): ");
        TaskType type = null;

        int taskTypeChoice = scanner.nextInt();

        switch (taskTypeChoice) {
            case 1:
                type = TaskType.PERSONAL;
                break;
            case 2:
                type = TaskType.WORKING;
                break;
            default:
                System.out.println("Тип задачи введен не коректно");
                scanner.close();
        }
        return type;
    }

    private static LocalDateTime inputTaskTime(Scanner scanner) {
        System.out.println("Введите дату и время задачи в формате dd.MM.yyyy HH.mm");

        if (scanner.hasNext(DATE_TIME_PATTERN)) {
            String dateTime = scanner.next(DATE_TIME_PATTERN);
            return LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
        }else {
            System.out.println("Введите дату и время задачи в формате dd.MM.yyyy HH.mm");
            scanner.close();
            return null;
        }
    }

    private static int inputRepeatability(Scanner scanner) {
        System.out.println("Выведите повторяемость задачи (1 - однократная, 2 - ежедневная, 3 - ежнедельна," +
                " 4 - ежемесячная, 5 - ежегодная): ");
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            System.out.println("Введите повторяеиость задачи");
            scanner.close();
        }
        return -1;
    }


    private static void createTask(String title, String description, TaskType type, LocalDateTime timeTask,
                                   int repeatability) {
        Task task = null;
        try {
            switch (repeatability) {
                case 1:
                    task = new  OneTimeTask(title, description,timeTask, type);
                    break;
                case 2:
                    task = new DailyTask(title, description,timeTask, type);
                    break;
                case 3:
                    task = new WeeklyTask(title, description,timeTask, type);
                    break;
                case 4:
                    task = new MonthlyTask(title, description,timeTask, type);
                    break;
                case 5:
                    task = new YearlyTask(title, description,timeTask, type);
                    break;
                default:
                    System.out.println("Повторяемость задачи введена некоректно");
            }
        } catch (IncorrectArgumentException e){
            System.out.println(e.getMessage());
        }

        if (task != null) {
            taskService.add(task);
            System.out.println("Задача добавлена");
        } else {
            System.out.println("Введенены не коректные данные по задаче");
        }
    }

    private static void printTaskByDey(Scanner scanner) {
        System.out.println("Введите дату в формате dd.MM.yyyy");

        if (scanner.hasNext(DATE_PATTERN)) {
            String dateTime = scanner.next(DATE_PATTERN);
            LocalDate inputDate =  LocalDate.parse(dateTime, DATE_FORMATTER);

            Collection<Task> tasksByDey = taskService.getAllByDate(inputDate);

            for (Task task : tasksByDey) {
                System.out.println(task);
            }
        }else {
            System.out.println("Введите дату в формате dd.MM.yyyy");
            scanner.close();
        }
    }
    private static void deleteTask(Scanner scanner) {
        System.out.println("Введите id задачи для удаления");

        int id = scanner.nextInt();
        try {
            taskService.remove(id);
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }



    private static void printMenu() throws IncorrectArgumentException {
        System.out.println("Добавьте задачу, удалите задачу, Получить задачу на указанный день, Выход");
    }
    public static void main(String[] args) throws IncorrectArgumentException {


        System.out.println("Курсовая №2");
        System.out.println();



        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.println("Выберите тип меню ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            deleteTask(scanner);
                            break;
                        case 3:
                            printTaskByDey(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выьерите пункт меню из списка");
                }
            }
        }






    }




}