package com.group9.server.Notes;

public interface IStudentNotes {
    void viewNotes(String studentId, String courseId);

    void addNotes(String studentId, String courseId, String notes);

    String getCourseInput();

    String getNotesText();
}
