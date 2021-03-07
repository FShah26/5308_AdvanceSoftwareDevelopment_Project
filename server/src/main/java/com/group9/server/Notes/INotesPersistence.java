package com.group9.server.Notes;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface INotesPersistence {
    ResultSet fetchNotes(String studentID, String courseID) throws SQLException;
    void insertNotes(String studentID, String courseID, String notes);
}
