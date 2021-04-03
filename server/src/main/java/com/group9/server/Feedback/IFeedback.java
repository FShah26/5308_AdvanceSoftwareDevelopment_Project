package com.group9.server.Feedback;

import java.sql.SQLException;

public interface IFeedback {
    void viewFeedback(String facultyId);

    void addFeedback(String userId, String userName, String feedback, String facultyId) ;

    String  getStudentName();

    String getFeedbackText();

    String getFacultyID();
}
