package com.group9.server.Feedback;
public interface IFeedbackLogic {
    FeedbackList viewFeedback(String faculty_id);

    String addFeedback(String user_id, String userName, String feedback, String faculty_id);
}
