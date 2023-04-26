package ru.bestaford.ariovale.entity;

import jakarta.persistence.*;
import ru.bestaford.ariovale.util.PermissionLevel;
import ru.bestaford.ariovale.util.Sex;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

@Entity
@Table(name = "accounts")
public class Account {

    public final static Pattern NAME_PATTERN = Pattern.compile("^\\p{L}{1,20}\\s+\\p{L}{1,20}$");
    public final static Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!\\\"#$%&'()*+,\\-./:;<=>?@\\[\\\\\\]^_`{|}~])\\S{8,}$");
    public final static int MIN_AGE = 18;
    public final static int MAX_AGE = 80;

    @Id
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PermissionLevel permissionLevel;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date lastDate;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private UUID uuid;

    private String xuid;

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

    public PermissionLevel getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(PermissionLevel permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getXuid() {
        return xuid;
    }

    public void setXuid(String xuid) {
        this.xuid = xuid;
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