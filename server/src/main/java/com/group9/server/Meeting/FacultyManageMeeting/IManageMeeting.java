package com.group9.server.Meeting.FacultyManageMeeting;

import com.group9.server.IExecuteAction;

public interface IManageMeeting extends IExecuteAction {

    void display(String username);

    void selectMenu();

    void checkInput(String selection);

    void displayInvalidMenuOptionMsg();
}

