package com.group9.server.Notes;

public interface INotesLogic {
    NotesList viewNotes(String studentId, String courseId);

    String addNotes(String studentId, String courseId, String notes);
}
