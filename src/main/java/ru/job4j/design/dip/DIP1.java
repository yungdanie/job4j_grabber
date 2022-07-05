package ru.job4j.design.dip;

/**
 * Нарушение принципа DIP из-за привязанности к реалезации.
 */

public class DIP1 {

    public static void main(String[] args) {
        PublicTransport publicTransport = new PublicTransport();
        publicTransport.getToDestination();
    }

    public static class PublicTransport {
        Transport transport = new Transport();

        public void getToDestination() {
            transport.ride();
        }
    }

    public static class Transport {
        public void ride() {

        }
    }
}
