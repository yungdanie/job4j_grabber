package ru.job4j.design.ocp;

import java.util.List;

/**
 * При расширении функционала данного класса мы не можем легко наследоваться от Administrator,
 * две этих сущности обладают общими признаками, но для правильного использования нужно было сделать
 * общий интерфейс Employee и реализовывать его.
 */
public class OCP {

    private static class Administrator {
        public void admin() {
            System.out.println("working...");
        }
    }

    private static class Worker extends Administrator {
        @Override
        public void admin() {
            System.out.println("Admining");
        }
    }

    public static void main(String[] args) {

        List<Administrator> employers = List.of(new Administrator());
        employers.forEach(Administrator::admin);
    }
}
