package com.group9.server.Notes;

import com.group9.server.IExecuteAction;

public interface IStudentNotes extends IExecuteAction {
    void viewNotes(String studentId, String courseId);

    void addNotes(String studentId, String courseId, String notes);

    String getCourseInput();

    String getNotesText();
}
