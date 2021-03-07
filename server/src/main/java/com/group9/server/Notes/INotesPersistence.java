package com.group9.server.Notes;

import java.sql.SQLException;

public interface INotesPersistence {
    void fetchNotes(String studentID, String courseID) throws SQLException;
    void insertNotes(String studentID, String courseID, String notes);
}
