package ru.job4j.design.lsp;

/**
 * В данном классе мы проверяем является ли класс конкретным наследником и выполняем логику функции.
 * Это нарушает принцип LSP.
 */
public class SquareArea {

    public static int calculate(Model2D model2D) {
        int res = 0;
        if (model2D instanceof Quadrate) {
            System.out.println("Quadrate");
            res = model2D.height * model2D.length;
        } /** else if (model2D instanceof Triangle) и так далее...
         */
        return res;
    }

    public static void main(String[] args) {
        calculate(new Quadrate(12, 12));
    }
}
