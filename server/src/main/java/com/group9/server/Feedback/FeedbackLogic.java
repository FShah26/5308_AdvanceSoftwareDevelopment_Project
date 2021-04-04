package com.group9.server.Feedback;

import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FeedbackLogic implements IFeedbackLogic {

    IFeedbackPersistence persistence;

    public FeedbackLogic(IFeedbackPersistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public FeedbackList viewFeedback(String facultyId) {
        FeedbackList feedback = new FeedbackList();

        try {
            ResultSet set = persistence.fetchFeedback(facultyId);

            if (set != null) {
                while (set.next()) {
                    feedback.feedback.add(set.getString(1));
                    feedback.feedback.add(set.getString(2));
                }
            }
            return feedback;

        } catch (SQLException throwables) {
            System.out.println("Fetching feedback Failed");
            throwables.printStackTrace();
        }

        return feedback;
    }


    @Override
    public String addFeedback(String userId, String userName, String feedback, String facultyId) {
        String message = "";
        try {
            message = persistence.insertFeedback(userId, userName, feedback, facultyId);
        } catch (SQLException exception) {
            System.out.println("Adding feedback failed");
            exception.printStackTrace();
        }
        return message;
    }
}
