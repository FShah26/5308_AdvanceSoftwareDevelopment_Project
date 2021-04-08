package com.group9.server.Dashboard;

import com.group9.server.HomePage.UserConstants;
import com.group9.server.IExecuteAction;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class FacultyDashboard implements IDashboard {
    private static final String VIEW_NOTIFICATION ="1";
    private static final String MANAGE_LECTURE ="2";
    private static final String FACULTY_ANNOUNCEMENT ="3";
    private static final String MANAGE_MEETING ="4";
    private static final String FEEDBACK ="5";
    private static final String QUIZ ="6";
    private static final int SYSTEM_EXIT =0;

    private final String role;
    IInputValidator facultyValidator;
    IExecuteAction feedback;
    IExecuteAction quiz;
    IExecuteAction manageLecture;
    IExecuteAction viewUserNotifications;
    IExecuteAction facultyAnnouncement;
    IExecuteAction manageMeeting;
    Map<String, IExecuteAction> action = new HashMap<>();
    private String userName;

    public FacultyDashboard(IInputValidator facultyValidator, IExecuteAction viewUserNotifications, IExecuteAction manageLecture, IExecuteAction facultyAnnouncement, IExecuteAction manageMeeting, IExecuteAction feedback, IExecuteAction quiz) {
        this.facultyValidator = facultyValidator;
        this.feedback = feedback;
        this.viewUserNotifications = viewUserNotifications;
        this.manageLecture = manageLecture;
        this.facultyAnnouncement = facultyAnnouncement;
        this.quiz = quiz;
        this.manageMeeting = manageMeeting;
        this.role = UserConstants.FACULTY;
        action.put(VIEW_NOTIFICATION, this.viewUserNotifications);
        action.put(MANAGE_LECTURE, this.manageLecture);
        action.put(FACULTY_ANNOUNCEMENT, this.facultyAnnouncement);
        action.put(MANAGE_MEETING, this.manageMeeting);
        action.put(FEEDBACK, this.feedback);
        action.put(QUIZ, this.quiz);
    }

    @Override
    public void showDashboard() {
        System.out.println("************************************************");
        System.out.println("               FACULTY DASHBOARD                ");
        System.out.println("************************************************");

        System.out.println("Press 1 --> Notifications");
        System.out.println("Press 2 --> Manage Lectures");
        System.out.println("Press 3 --> Send Announcement");
        System.out.println("Press 4 --> Manage Meetings");
        System.out.println("Press 5 --> View Feedback");
        System.out.println("Press 6 --> Add question for a quiz");
        System.out.println("Press 7 --> Log out");
        System.out.println("Choose Option:");
        selectMenu();
    }

    @Override
    public void setUsername(String userName) {
        this.userName = userName;
    }

    @Override
    public void selectMenu() {
        Scanner sc = new Scanner(System.in);
        String menuOption = sc.nextLine();
        checkInput(menuOption);
    }

    @Override
    public void checkInput(String selection) {
        if (this.facultyValidator.validate(selection)) {
            IExecuteAction dashboardAction = action.get(selection);
            if (null == dashboardAction) {
                System.out.println("Logging out...");
                System.out.println("Logged out successfully...");
                System.exit(SYSTEM_EXIT);
            } else {
                dashboardAction.execute(this.role, this.userName);
            }
            showDashboard();
        } else {
            displayInvalidMenuOptionMsg();
            selectMenu();
        }
    }

    @Override
    public void displayInvalidMenuOptionMsg() {
        System.out.println("Invalid Option! Please choose a valid option from menu.");
    }
}
