package com.group9.server.Feedback;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IFeedbackPersistence {
    ResultSet fetchFeedback(String facultyId) throws SQLException;

    String insertFeedback(String userId, String userName, String feedback, String facultyId) throws SQLException;

}
