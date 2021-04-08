package com.group9.server.Notes;

import java.util.ArrayList;

public class NotesList {
    String subject;
    ArrayList<String> notes;

    public NotesList(String courseId) {
        this.subject = courseId;
        this.notes = new ArrayList<>();
    }
}
