package ru.bestaford.ariovale.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import ru.bestaford.ariovale.dto.Account;

import java.sql.SQLException;

@Singleton
public final class AccountDao extends BaseDaoImpl<Account, String> {

    @Inject
    public AccountDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Account.class);
        TableUtils.createTableIfNotExists(connectionSource, Account.class);
    }
}