package com.group9.server.CourseCreation;

import com.group9.server.IExecuteAction;

public interface ICreateCourse extends IExecuteAction {
    void creation();

    void selectMenu();

    void validateInput(String input);

    void displayInvalidMenuOptionMsg();
}
