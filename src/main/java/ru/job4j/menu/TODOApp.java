package ru.job4j.menu;

public class TODOApp {
    private final Menu menu;
    private final MenuPrinter menuPrinter;

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
}
