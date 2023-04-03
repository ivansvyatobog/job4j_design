package ru.job4j.serialization.json;

import ru.job4j.serialization.java.Contact;

import java.util.Arrays;

public class Person {
    private final String name;
    private final boolean married;
    private final int age;
    private final Contact contact;
    private final String[] statuses;

    public Person(String name, boolean married, int age, Contact contact, String[] statuses) {
        this.name = name;
        this.married = married;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name='" + name + '\''
                + ", married=" + married
                + ", age=" + age
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }
}
