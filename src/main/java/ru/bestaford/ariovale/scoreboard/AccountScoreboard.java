package ru.bestaford.ariovale.scoreboard;

import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.entity.ProfileData;

public class AccountScoreboard extends CustomScoreboard {

    public AccountScoreboard(Account account) {
        super(PORTAL_NAME_COLORIZED);
        update(account);
    }

    public void update(Account account) {
        ProfileData profileData = account.getProfileData();
        removeAllLine(false);
        addLine();
        addLine("Name: " + account.getName());
        addLine("Age: " + profileData.getAge().toString());
        addLine("Money: " + profileData.getMoney().toString());
    }
}