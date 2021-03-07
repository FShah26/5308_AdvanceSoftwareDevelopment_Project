package com.group9.server.Notes;

public interface INotesLogic {
    NotesList viewNotes(String studentID, String courseID);
    void addNotes(String studentID, String courseID, String notes);
}
