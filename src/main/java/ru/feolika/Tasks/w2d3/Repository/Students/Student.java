package ru.feolika.Tasks.w2d3.Repository.Students;

import java.sql.Date;

/**
 * студент
 */
public class Student {
    private int ID;
    private String name;
    private String surname;
    private Date birthday;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Student(String name, String surname, Date birthday) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
               "ID=" + ID +
               ", name='" + name + '\'' +
               ", surname='" + surname + '\'' +
               ", birthday=" + birthday +
               '}';
    }
}
