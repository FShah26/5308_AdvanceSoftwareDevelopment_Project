package com.group9.server.Feedback;

public interface IFeedbackLogic {
    FeedbackList viewFeedback(String facultyId);

    String addFeedback(String userId, String userName, String feedback, String facultyId);
}
