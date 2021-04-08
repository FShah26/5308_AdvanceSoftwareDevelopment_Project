package com.group9.server.Feedback;

import com.group9.server.HomePage.UserConstants;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Scanner;

@Component
public class Feedback implements IFeedback {

    private static final int FEEDBACK_SIZE = 0;
    IFeedbackLogic feedbackLogic;

    public Feedback(IFeedbackLogic feedbackLogic) {
        this.feedbackLogic = feedbackLogic;
    }

    @Override
    public void viewFeedback(String facultyId) {
        FeedbackList list = feedbackLogic.viewFeedback(facultyId);

        if (list.feedback.size() == FEEDBACK_SIZE) {
            System.out.println("Looks like you don't have any feedback");
        } else {
            ArrayList<String> feedbackList = list.feedback;
            for (int i = 0; i < feedbackList.size(); i++) {
                String student = feedbackList.get(i);
                String feedback = feedbackList.get(i + 1);
                System.out.println("--------------------");
                System.out.println(student + ":-" + feedback);
                i++;
            }
        }
    }

    @Override
    public void addFeedback(String userId, String userName, String feedback, String facultyId) {
        String message = feedbackLogic.addFeedback(userId, userName, feedback, facultyId);
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
    public String getFacultyId() {
        System.out.println("Enter the faculty ID you wish to send feedback to:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public void execute(String userRole, String userId) {
        if (userRole.equals(UserConstants.FACULTY)) {
            viewFeedback(userId);
        } else if (userRole.equals(UserConstants.STUDENT)) {
            String studentName = getStudentName();
            String feedback = getFeedbackText();
            String faculty = getFacultyId();
            addFeedback(userId, studentName, feedback, faculty);
        }
    }

}
