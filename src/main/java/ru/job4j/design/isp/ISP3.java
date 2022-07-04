package ru.job4j.design.isp;

/**
 * Очевидное нарушение ISP из-за слишком перегруженного интерфейса.
 */

public class ISP3 {
    public interface Employee {
        void doWork();
        Object createPlan();
        Object createConstruct();
        void draftPlan();
        void build();
    }

    public class Employer implements Employee {

        @Override
        public void doWork() {

        }

        @Override
        public Object createPlan() {
            return null;
        }

        @Override
        public Object createConstruct() {
            return null;
        }

        @Override
        public void draftPlan() {

        }

        @Override
        public void build() {

        }
    }
}
