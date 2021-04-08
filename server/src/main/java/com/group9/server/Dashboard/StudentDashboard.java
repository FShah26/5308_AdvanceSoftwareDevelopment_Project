package com.group9.server.Dashboard;

import com.group9.server.HomePage.UserConstants;
import com.group9.server.IExecuteAction;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.out;

@Component
public class StudentDashboard implements IDashboard {

    private static final String VIEW_NOTIFICATION = "1";
    private static final String UPCOMING_LECTURE = "2";
    private static final String VIEW_ANNOUNCEMENT = "3";
    private static final String NOTES = "4";
    private static final String REQUEST_MEETING = "5";
    private static final String FEEDBACK = "6";
    private static final String QUIZ = "7";
    private static final String LOGOUT = "8";
    
    private final String role;
    IInputValidator studentValidator;
    IExecuteAction feedback;
    IExecuteAction viewAnnouncements;
    IExecuteAction viewUserNotifications;
    IExecuteAction upcomingLectureDisplay;
    IExecuteAction studentNotes;
    IExecuteAction requestMeeting;
    IExecuteAction quizAssessment;
    Map<String, IExecuteAction> action = new HashMap<>();
    private String userName;

    public StudentDashboard(IInputValidator studentValidator, IExecuteAction viewUserNotifications, IExecuteAction upcomingLectureDisplay, IExecuteAction viewAnnouncements, IExecuteAction studentNotes, IExecuteAction requestMeeting, IExecuteAction feedback, IExecuteAction quizAssessment) {
        this.role = UserConstants.STUDENT;
        this.studentValidator = studentValidator;
        this.feedback = feedback;
        this.viewUserNotifications = viewUserNotifications;
        this.upcomingLectureDisplay = upcomingLectureDisplay;
        this.viewAnnouncements = viewAnnouncements;
        this.studentNotes = studentNotes;
        this.requestMeeting = requestMeeting;
        this.quizAssessment = quizAssessment;
        action.put(VIEW_NOTIFICATION, this.viewUserNotifications);
        action.put(UPCOMING_LECTURE, this.upcomingLectureDisplay);
        action.put(VIEW_ANNOUNCEMENT, this.viewAnnouncements);
        action.put(NOTES, this.studentNotes);
        action.put(REQUEST_MEETING, this.requestMeeting);
        action.put(FEEDBACK, this.feedback);
        action.put(QUIZ, this.quizAssessment);
        action.put(LOGOUT, null);
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
        if (this.studentValidator.validate(selection)) {
            IExecuteAction dashboardAction = action.get(selection);
            if (null == dashboardAction) {
                //logout
            } else {
                dashboardAction.execute(this.role, this.userName);
            }
            showDashboard();
        } else {
            displayInvalidMenuOptionMsg();
            selectMenu();
        }
    }

    public void displayInvalidMenuOptionMsg() {
        out.println("Invalid Option! Please choose a valid option from menu.");
    }
}
