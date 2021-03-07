package com.group9.server.Notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class NotesLogic implements INotesLogic{

    NotesPersistence persistence;

    @Autowired
    public NotesLogic(NotesPersistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public void viewNotes(String studentID, String courseID) {
        try {
            persistence.fetchNotes(studentID, courseID);
        } catch (SQLException throwables) {
            System.out.println("Fetching Notes Failed");
            throwables.printStackTrace();
        }
    }

    @Override
    public void addNotes(String studentID, String courseID, String notes) {

    }
}
