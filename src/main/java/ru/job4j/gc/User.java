package ru.job4j.gc;

public class User {

    public User(String firstName, int age) {
        this.firstName = firstName;
        this.age = age;
    }

    public User() {
    }

    private String firstName;

    private int age;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Удаление " + age);
    }
}


