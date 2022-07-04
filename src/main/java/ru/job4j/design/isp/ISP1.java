package ru.job4j.design.isp;

/**
 * Нарушение принципа ISP состоит в том, что для каждой реализации будут определены ненужные методы.
 * Для машины лететь и плыть - для лодки ехать и лететь.
 */

public class ISP1 {
    public interface Transport {
        public void drive();
        public void fly();
        public void sail();
    }
    public class Car implements Transport {

        @Override
        public void drive() {

        }

        @Override
        public void fly() {

        }

        @Override
        public void sail() {

        }
    }

    public class Boat implements Transport {

        @Override
        public void drive() {

        }

        @Override
        public void fly() {

        }

        @Override
        public void sail() {

        }
    }
}
