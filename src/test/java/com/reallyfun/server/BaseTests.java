package com.reallyfun.server;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public abstract class BaseTests {
    protected static Integer randint(Integer min, Integer max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    protected static Double random(Double min, Double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    protected static Boolean randbool() {
        return randint(0, 1) == 1;
    }

    protected static String randstr(Integer length) {
        return UUID.randomUUID().toString()
                .replaceAll("-", "")
                .substring(0, length);
    }

    protected static String randstr() {
        return randstr(6);
    }

    protected static Date randdate() {
        Calendar cal = Calendar.getInstance();
        cal.set(randint(2000, 2020), randint(0, 11), randint(0, 28));
        return cal.getTime();
    }
}
