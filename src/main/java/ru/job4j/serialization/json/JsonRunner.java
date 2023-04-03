package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.serialization.java.Contact;

public class JsonRunner {
    public static void main(String[] args) {
        final Person person = new Person("John", true, 32, new Contact(123000, "111-333"),
                new String[]{"Employee", "Java Developer", "Team Lead"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));
        final String personJson = gson.toJson(person);
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);
    }
}