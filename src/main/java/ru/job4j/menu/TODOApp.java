package ru.job4j.menu;

import java.util.Scanner;

public class TODOApp {
    private final Menu menu;
    private final MenuPrinter menuPrinter;

    private final static int EXIT = 0;
    private final static int ADD_TASK = 1;
    private final static int SHOW_ALL_TASKS = 2;

    private final static String ACTIONS = """
                Выберите действие:
                0. Выход
                1. Добавить задачу
                2. Показать задачи""";

    public TODOApp(Menu menu) {
        this.menu = menu;
        this.menuPrinter = new SimpleMenuPrinter();
    }

    public void addTask(String parentTask, String childTask, String delegatePrint) {
        menu.add(parentTask, childTask, () -> System.out.println(delegatePrint));
    }

    public void showAllTasks() {
        menuPrinter.print(menu);
    }

    public static void main(String[] args) {
        TODOApp todoApp = new TODOApp(new SimpleMenu());
        Scanner scanner = new Scanner(System.in);
        System.out.println(ACTIONS);
        int res = scanner.nextInt();
        while (res != EXIT) {
            if (res == ADD_TASK) {
                System.out.println("Введите надзадачу или ROOT, если это корневая задача");
                String parent = scanner.next();
                if (parent.equals("ROOT")) {
                    parent = Menu.ROOT;
                }
                System.out.println("Введите название задачи");
                String child = scanner.next();
                todoApp.addTask(parent, child, "");
            } else if (res == SHOW_ALL_TASKS) {
                todoApp.showAllTasks();
            }
            System.out.println(ACTIONS);
            res = scanner.nextInt();
        }
    }
}
