package com.group9.server.Feedback;

import java.sql.SQLException;

public interface IFeedback {
    void viewFeedback(String faculty_id);

    void addFeedback(String user_id, String userName, String feedback, String faculty_id) throws SQLException;

    String  getStudentName();

    String getFeedbackText();

    String getFacultyID();
}
