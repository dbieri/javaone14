package org.dbieri.javaone14.streams;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dbieri on 28.09.14.
 */
public class Person {
    public static SimpleDateFormat FORMAT = new SimpleDateFormat("dd.MM.yy");

    private String name;
    private Date birthday;

    public Person(String name, String birthdayStr) {
        this.name = name;
        try {
            this.birthday = FORMAT.parse(birthdayStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
