package com.group9.server.Dashboard;

public interface IDashboard {
    void showDashboard();

    void setUsername(String username);

    void selectMenu();

    void checkInput(String selection);

    void displayInvalidMenuOptionMsg();
}
