package com.group9.server.Feedback;

public interface IFeedback {
    void viewFeedback(String faculty_id);

    void addFeedback(String user_id, String userName, String feedback, String faculty_id);

    String  getStudentName();

    String getFeedbackText();

    String getFacultyID();
}
