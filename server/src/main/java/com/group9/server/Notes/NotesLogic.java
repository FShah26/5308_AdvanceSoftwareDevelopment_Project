package com.group9.server.Notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class NotesLogic implements INotesLogic{

    NotesPersistence persistence;

    @Autowired
    public NotesLogic(NotesPersistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public NotesList viewNotes(String studentID, String courseID) {
        NotesList subjectNotes = new NotesList(courseID);

        try {
            ResultSet set = persistence.fetchNotes(studentID, courseID);

            while(set.next()){
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
    public void addNotes(String studentID, String courseID, String notes) {

    }
}
