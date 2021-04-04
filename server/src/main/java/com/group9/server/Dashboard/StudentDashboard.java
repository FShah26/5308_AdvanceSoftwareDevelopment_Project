package com.group9.server.Dashboard;

import com.group9.server.HomePage.UserConstants;
import com.group9.server.IExecuteAction;
import com.group9.server.Quiz.Student.IQuizAssessment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.out;

@Component
public class StudentDashboard implements IDashboard {
    private final String role;
    InputValidator validator;
    IExecuteAction feedback;
    IExecuteAction viewAnnouncements;
    IExecuteAction viewUserNotifications;
    IExecuteAction upcomingLectureDisplay;
    IExecuteAction studentNotes;
    IExecuteAction requestMeeting;
    IExecuteAction quizAssessment;
    Map<String, IExecuteAction> action = new HashMap<>();
    private String userName;

    public StudentDashboard(InputValidator validator, IExecuteAction viewUserNotifications, IExecuteAction upcomingLectureDisplay, IExecuteAction viewAnnouncements, IExecuteAction studentNotes, IExecuteAction requestMeeting, IExecuteAction feedback, IExecuteAction quizAssessment) {
        this.role = UserConstants.STUDENT;
        this.validator = validator;
        this.feedback = feedback;
        this.viewUserNotifications = viewUserNotifications;
        this.upcomingLectureDisplay = upcomingLectureDisplay;
        this.viewAnnouncements = viewAnnouncements;
        this.studentNotes = studentNotes;
        this.requestMeeting = requestMeeting;
        this.quizAssessment = quizAssessment;
        action.put("1", this.viewUserNotifications);
        action.put("2", this.upcomingLectureDisplay);
        action.put("3", this.viewAnnouncements);
        action.put("4", this.studentNotes);
        action.put("5", this.requestMeeting);
        action.put("6", this.feedback);
        action.put("7", this.quizAssessment);
        action.put("8", null);
    }

    @Override
    public void showDashboard() {
        System.out.println("************************************************");
        System.out.println("               STUDENT DASHBOARD                ");
        System.out.println("************************************************");

        System.out.println("Press 1 --> Notifications");
        System.out.println("Press 2 --> Up-coming lectures");
        System.out.println("Press 3 --> Announcements");
        System.out.println("Press 4 --> Notes (View/Add)");
        System.out.println("Press 5 --> Request Meeting");
        System.out.println("Press 6 --> Send Feedback");
        System.out.println("Press 7 --> Tests");
        System.out.println("Press 8 --> Log out");
        System.out.println("Choose option : ");
        selectMenu();
    }

    @Override
    public void setUsername(String userName) {
        this.userName = userName;
    }


    public void selectMenu() {
        Scanner sc = new Scanner(System.in);
        String menuOption = sc.nextLine();
        checkInput(menuOption);
    }

    public void checkInput(String selection) {
        if (this.validator.validate(selection)) {
            IExecuteAction dashboardAction = action.get(selection);
            if (null == dashboardAction) {
                //logout
            } else {
                dashboardAction.execute(this.role, this.userName);
            }

        } else {
            displayInvalidMenuOptionMsg();
            selectMenu();
        }
    }

    public void displayInvalidMenuOptionMsg() {
        out.println("Invalid Option! Please choose a valid option from menu.");
    }
}
