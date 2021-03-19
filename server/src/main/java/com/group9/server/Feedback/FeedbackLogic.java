package com.group9.server.Feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FeedbackLogic implements IFeedbackLogic {

    IFeedbackPersistence persistence;

    @Autowired
    public FeedbackLogic(IFeedbackPersistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public FeedbackList viewFeedback(String faculty_id) {
        FeedbackList feedback = new FeedbackList();

        try {
            ResultSet set = persistence.fetchFeedback(faculty_id);

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
    public String addFeedback(String user_id, String userName, String feedback, String faculty_id) {
        String message = "";
        try {
            message = persistence.insertFeedback(user_id, userName, feedback, faculty_id);
        } catch (SQLException exception) {
            System.out.println("Adding feedback failed");
            exception.printStackTrace();
        }
        return message;
    }
}
