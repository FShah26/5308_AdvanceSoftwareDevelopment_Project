package com.group9.server.Notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentNotes implements IStudentNotes{
    @Autowired
    INotesLogic notesLogic;

    @Autowired
    public StudentNotes(INotesLogic notesLogic) {
        this.notesLogic = notesLogic;
    }

    @Override
    public void viewNotes(String studentID, String courseID) {
        notesLogic.viewNotes(studentID, courseID);
    }

    @Override
    public void addNotes(String studentID, String courseID, String notes) {
        notesLogic.addNotes(studentID, courseID, notes);
    }
}
