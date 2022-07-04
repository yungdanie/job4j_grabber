package ru.job4j.design.isp;

/**
 * Здесь находится пример явного нарушения принципа ISP из-за неправильно выбранной абстракции, т.к.
 * домашний сервер предназначенный для хранения файла обязан также реализовывать ненужные ему методы.
 */

public class ISP2 {
    public interface Server {
        Object getFile();
        boolean loadFile();
        void startSite();
        void startServer();
        void startControlPanel();
    }

    public class HomeStorageServer implements Server {

        @Override
        public Object getFile() {
            return null;
        }

        @Override
        public boolean loadFile() {
            return false;
        }

        @Override
        public void startSite() {

        }

        @Override
        public void startServer() {

        }

        @Override
        public void startControlPanel() {

        }

    }
}
