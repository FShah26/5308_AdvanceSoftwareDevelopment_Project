package com.group9.server.Notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class NotesLogic implements INotesLogic {

    INotesPersistence persistence;

    @Autowired
    public NotesLogic(INotesPersistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public NotesList viewNotes(String studentID, String courseID) {
        NotesList subjectNotes = new NotesList(courseID);

        try {
            ResultSet set = persistence.fetchNotes(studentID, courseID);

            while (set.next()) {
                subjectNotes.notes.add(set.getString(1));
            }

            return subjectNotes;
        } catch (SQLException throwables) {
            System.out.println("Fetching Notes Failed");
            throwables.printStackTrace();
        }

        return subjectNotes;
    }

    @Override
    public String addNotes(String studentID, String courseID, String notes) {
        String message = "";
        try {
            message = persistence.insertNotes(studentID, courseID, notes);
        } catch (SQLException exception) {
            System.out.println("Adding notes failed");
            exception.printStackTrace();
        }
        return message;
    }
}
