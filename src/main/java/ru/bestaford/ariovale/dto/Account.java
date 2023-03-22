package ru.bestaford.ariovale.dto;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import ru.bestaford.ariovale.dao.AccountDao;

@DatabaseTable(tableName = "accounts", daoClass = AccountDao.class)
public class Account {

    @DatabaseField(id = true)
    private String id;

    public Account() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}