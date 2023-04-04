package ru.job4j.serialization.json;

import ru.job4j.serialization.java.Contact;
import org.json.JSONObject;

public class JsonRunner {
    public static void main(String[] args) {

        final Person person = new Person("John", true, 32, new Contact(123000, "111-333"),
                new String[]{"Employee", "Java Developer", "Team Lead"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", person.getName());
        jsonObject.put("married", person.isMarried());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", person.getContact());
        jsonObject.put("statuses", person.getStatuses());
        System.out.println(jsonObject);
    }
}