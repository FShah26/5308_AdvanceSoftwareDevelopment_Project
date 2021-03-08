package com.group9.server.Notes;

import java.util.ArrayList;

public class NotesList {
    String subject;
    ArrayList<String> notes;

    public NotesList(String courseID) {
        this.subject = courseID;
        this.notes = new ArrayList<>();
    }
}
