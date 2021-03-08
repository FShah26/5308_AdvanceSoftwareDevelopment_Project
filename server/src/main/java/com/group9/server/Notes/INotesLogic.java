package com.group9.server.Notes;

public interface INotesLogic {
    NotesList viewNotes(String studentID, String courseID);

    String addNotes(String studentID, String courseID, String notes);
}
