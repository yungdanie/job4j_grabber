package ru.job4j.design.ocp;

/**
 * При наследовании класса OCP3 мы получаем метод, который нужно переопределить. Из-за того, что тип возвращаемого
 * значения "Employee" мы не можем создать нужную нам реализацию т.к. привязываемся к классу Employee а не к
 * общему интерфейсу для работников, что нарушает принципы OCP.
 */

public class OCP3 {
    public Employee doSomeMod(Employee employee) {
        return new Employee();
    }

    public class OCP4 extends OCP3 {
        @Override
        public Employee doSomeMod(Employee employee) {
            return super.doSomeMod(employee);
        }
    }
}
