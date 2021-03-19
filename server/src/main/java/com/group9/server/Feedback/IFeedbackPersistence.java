package com.group9.server.Feedback;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IFeedbackPersistence {
    ResultSet fetchFeedback(String faculty_id) throws SQLException;

    String insertFeedback(String user_id, String userName, String feedback, String faculty_id) throws SQLException;

}
