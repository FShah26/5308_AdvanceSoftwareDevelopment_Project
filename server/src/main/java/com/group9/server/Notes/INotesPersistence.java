package com.group9.server.Notes;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface INotesPersistence {
    ResultSet fetchNotes(String studentId, String courseId) throws SQLException;

    String insertNotes(String studentId, String courseId, String notes) throws SQLException;
}
