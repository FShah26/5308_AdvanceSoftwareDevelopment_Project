package com.group9.server.Notes;

public interface INotesLogic {
    void viewNotes(String studentID, String courseID);
    void addNotes(String studentID, String courseID, String notes);
}
