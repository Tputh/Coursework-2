import Model.*;
import exception.IncorrectArgumentException;
import service.ServiceTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final ServiceTask serviceTask = new ServiceTask();
    private static final Pattern DATE_TIME_PATTERN =  Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}\\:\\d{2}");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm");
    public static void main(String[] args)  {

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
                            break;
                        case 3:
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выьерите пункт меню из списка");
                }
            }
        } catch (IncorrectArgumentException e) {
            e.printStackTrace();
        }
    }




    private static void inputTask(Scanner scanner) throws IncorrectArgumentException {
        scanner.useDelimiter("\n");
        System.out.println("Выведите название задачи");
        String title = scanner.next();
        System.out.println("Выведите описание задачи");
        String description = scanner.next();
        System.out.println("Выведите тип задачи (1 - лична, 2 - рабочая): ");
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
        }

        System.out.println("Введите дату и время задачи в формате dd.MM.yyyy HH.mm");

        LocalDateTime timeTask = null;
        if (scanner.hasNext(DATE_TIME_PATTERN)) {
            String dateTime = scanner.next(DATE_TIME_PATTERN);
             timeTask = LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
        }else {
            System.out.println("Введите дату и время задачи в формате dd.MM.yyyy HH.mm");
            scanner.close();
        }
        if (timeTask == null) {
            System.out.println("Введите дату и время");
            scanner.close();
        }
        System.out.println("Выведите повторяемость задачи (1 - однократная, 2 - ежедневная, 3 - ежнедельна," +
                " 4 - ежемесячная, 5 - ежегодная): ");
        Task task = null;
        if (scanner.hasNextInt()) {
           int repeatability = scanner.nextInt();
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
                    scanner.close();
            }
        }
        serviceTask.add(task);
        System.out.println("Задача добавлена");
    }

    private static void printMenu() {
        System.out.println("Добавьте задачу, удалите задачу, Получить задачу на указанный день, Выход");
    }

}