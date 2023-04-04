package ru.job4j.serialization.json;

import ru.job4j.serialization.java.Contact;
import java.util.Arrays;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    @XmlAttribute
    private String name;
    private boolean married;
    private int age;
    private Contact contact;
    @XmlElementWrapper(name = "statuses")
    @XmlElement(name = "status")
    private String[] statuses;

    public Person() {

    }

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
