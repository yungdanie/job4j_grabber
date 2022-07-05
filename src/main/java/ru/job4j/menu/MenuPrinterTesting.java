package ru.job4j.menu;

public class MenuPrinterTesting implements MenuPrinter {

    private final StringBuilder builder = new StringBuilder();

    public static final String INDENT = "----";
    public static final String SEP = System.lineSeparator();

    @Override
    public void print(Menu menu) {
        menu.forEach(x -> {
            builder.append(INDENT.repeat((x.getNumber().length() / 2)));
            builder.append(x.getNumber()).append(x.getName()).append(SEP);
        });
        System.out.println(builder);
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
