package ru.bestaford.ariovale.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {

    public static int MIN_AGE = 18;
    public static int MAX_AGE = 80;

    @Id
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

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
}