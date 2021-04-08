package com.group9.server.Notes;

import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class NotesLogic implements INotesLogic {
    private static final int COLUMN_INDEX = 1;
    INotesPersistence persistence;

    public NotesLogic(INotesPersistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public NotesList viewNotes(String studentId, String courseId) {
        NotesList subjectNotes = new NotesList(courseId);

        try {
            ResultSet set = persistence.fetchNotes(studentId, courseId);

            if (set != null) {
                while (set.next()) {
                    subjectNotes.notes.add(set.getString(COLUMN_INDEX));
                }
            }
            return subjectNotes;

        } catch (SQLException throwables) {
            System.out.println("Fetching Notes Failed");
            throwables.printStackTrace();
        }

        return subjectNotes;
    }

    @Override
    public String addNotes(String studentId, String courseId, String notes) {
        String message = "";
        try {
            message = persistence.insertNotes(studentId, courseId, notes);
        } catch (SQLException exception) {
            System.out.println("Adding notes failed");
            exception.printStackTrace();
        }
        return message;
    }
}
