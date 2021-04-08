package com.group9.server.Feedback;

import com.group9.server.IExecuteAction;

public interface IFeedback extends IExecuteAction {
    void viewFeedback(String facultyId);

    void addFeedback(String userId, String userName, String feedback, String facultyId);

    String getStudentName();

    String getFeedbackText();

    String getFacultyID();
}
