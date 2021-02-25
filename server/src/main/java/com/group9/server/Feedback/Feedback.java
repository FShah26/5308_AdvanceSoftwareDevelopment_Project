package com.group9.server.Feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Scanner;

@Component
public class Feedback implements IFeedback {
    IFeedbackLogic feedbackLogic;

    @Autowired
    public Feedback(IFeedbackLogic feedbackLogic) {
        this.feedbackLogic = feedbackLogic;
    }

    @Override
    public void viewFeedback(String faculty_id) {
        FeedbackList list = feedbackLogic.viewFeedback(faculty_id);

        if (list.feedback.size() == 0) {
            System.out.println("Looks like you don't have any feedback");
        } else {
            ArrayList<String> fb = list.feedback;
            for (int x = 0; x < fb.size(); x++) {
                String student = fb.get(x);
                String feedback = fb.get(x+1);
                System.out.println("--------------------");
                System.out.println(student + ":-" + feedback);
                x++;
            }
        }
    }

    @Override
    public void addFeedback(String user_id, String userName, String feedback,String faculty_id) {
        String message = feedbackLogic.addFeedback(user_id, userName, feedback, faculty_id);
        System.out.println(message);
    }
    @Override
    public String getStudentName() {
        System.out.println("Enter your name:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public String getFeedbackText() {
        System.out.println("Enter the text for the feedback: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    @Override
    public String getFacultyID() {
        System.out.println("Enter the faculty ID you wish to send feedback to:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}