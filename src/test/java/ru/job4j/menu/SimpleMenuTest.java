package ru.job4j.menu;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SimpleMenuTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;
    public static final String SEP = System.lineSeparator();

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertEquals(
                new Menu.MenuItemInfo(
                        "Сходить в магазин", List.of("Купить продукты"), STUB_ACTION, "1."
                ),
                menu.select("Сходить в магазин").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Купить продукты", List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."
                ),
                menu.select("Купить продукты").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Покормить собаку", List.of(), STUB_ACTION, "2."
                ),
                menu.select("Покормить собаку").get()
        );
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenAddThenPrintMenu() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        MenuPrinter printer = new MenuPrinterTesting();
        printer.print(menu);
        StringBuilder builder = new StringBuilder();
        builder.append("----").append("1.Сходить в магазин").append(SEP)
                .append("--------").append("1.1.Купить продукты").append(SEP)
                .append("------------").append("1.1.1.Купить хлеб").append(SEP)
                .append("------------").append("1.1.2.Купить молоко").append(SEP)
                .append("----").append("2.Покормить собаку").append(SEP);
        Assert.assertEquals(builder.toString(), printer.toString());
    }

    @Test
    public void whenAddThenSelect() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Пойти в школу", STUB_ACTION);
        menu.add(Menu.ROOT, "Остаться дома", STUB_ACTION);
        menu.add("Пойти в школу", "Учиться", STUB_ACTION);
        menu.add("Пойти в школу", "Бездельничать", STUB_ACTION);
        menu.add("Пойти в школу", "Отдыхать", STUB_ACTION);
        menu.add("Остаться дома", "Играть в компьютер", STUB_ACTION);
        assertEquals(
                new Menu.MenuItemInfo(
                        "Пойти в школу", List.of("Учиться", "Бездельничать", "Отдыхать"), STUB_ACTION, "1."
                ),
                menu.select("Пойти в школу").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Остаться дома", List.of("Играть в компьютер"), STUB_ACTION, "2."
                ),
                menu.select("Остаться дома").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Учиться", List.of(), STUB_ACTION, "1.1."
                ),
                menu.select("Учиться").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Бездельничать", List.of(), STUB_ACTION, "1.2."
                ),
                menu.select("Бездельничать").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Отдыхать", List.of(), STUB_ACTION, "1.3."
                ),
                menu.select("Отдыхать").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Играть в компьютер", List.of(), STUB_ACTION, "2.1."
                ),
                menu.select("Играть в компьютер").get()
        );
    }
}