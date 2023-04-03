package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Sergey";
        int age = 30;
        double height = 155.9;
        float weight = 80.7f;
        byte childrenCount = 3;
        char gender = 'M';
        boolean loginStatus = true;
        short salary = 7239;

        LOG.debug("User info: name - {}, age - {}, height - {}, weight - {}, children count - {}, gender - {}, "
                + "login status - {}, salary - {}", name, age, height, weight, childrenCount, gender, loginStatus, salary);
    }
}