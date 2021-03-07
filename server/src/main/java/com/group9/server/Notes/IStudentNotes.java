package com.group9.server.Notes;

public interface IStudentNotes {
    void viewNotes(String studentID, String courseID);

    void addNotes(String studentID, String courseID, String notes);

    String getCourseInput();
}
