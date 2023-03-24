package ru.bestaford.ariovale.dto;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import ru.bestaford.ariovale.dao.AccountDao;
import ru.bestaford.ariovale.util.PermissionLevel;
import ru.bestaford.ariovale.util.Sex;

import java.util.Date;
import java.util.UUID;

@DatabaseTable(tableName = "accounts", daoClass = AccountDao.class)
public final class Account {

    public static final int MIN_AGE = 18;
    public static final int MAX_AGE = 80;

    @DatabaseField(id = true)
    private String id;

    @DatabaseField(canBeNull = false)
    private String password;

    @DatabaseField(canBeNull = false)
    private PermissionLevel permissionLevel;

    @DatabaseField
    private Date registrationDate;

    @DatabaseField
    private Date lastDate;

    @DatabaseField
    private String address;

    @DatabaseField
    private UUID uuid;

    @DatabaseField
    private String xuid;

    @DatabaseField(canBeNull = false, unique = true)
    private String name;

    @DatabaseField(canBeNull = false)
    private Sex sex;

    @DatabaseField(canBeNull = false)
    private int age;

    public Account() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public String getXUID() {
        return xuid;
    }

    public void setXUID(String xuid) {
        this.xuid = xuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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