package com.group9.server.Notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentNotes implements IStudentNotes {
    INotesLogic notesLogic;

    @Autowired
    public StudentNotes(INotesLogic notesLogic) {
        this.notesLogic = notesLogic;
    }

    @Override
    public void viewNotes(String studentID, String courseID) {
        NotesList list = notesLogic.viewNotes(studentID, courseID);

        System.out.println("Notes for course ID: " + courseID);
        if (list.notes.size() == 0) {
            System.out.println("Looks like you don't have any notes for this course");
        } else {
            for (String note : list.notes) {
                System.out.println("--------------------");
                System.out.println(note);
            }
        }
    }

    @Override
    public void addNotes(String studentID, String courseID, String notes) {
        notesLogic.addNotes(studentID, courseID, notes);
    }
}
