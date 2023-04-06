package ru.bestaford.ariovale.entity;

import jakarta.persistence.*;
import ru.bestaford.ariovale.util.Sex;

@Entity
@Table(name = "accounts")
public class Account {

    public final static int MIN_AGE = 18;
    public final static int MAX_AGE = 80;

    @Id
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Sex sex;

    @Column(nullable = false)
    private int age;

    public Account() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}