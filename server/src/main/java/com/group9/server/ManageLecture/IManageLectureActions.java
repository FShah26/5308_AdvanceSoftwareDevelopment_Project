package com.group9.server.ManageLecture;

public interface IManageLectureActions {
    void getUserInputs();
    void showUserConfirmationOptions();
    boolean save();
    boolean getUserConfirmation();
    void displayInvalidMenuOptionMsg();
    void setFacultyId(String facultyId);



}
