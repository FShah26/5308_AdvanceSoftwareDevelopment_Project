package com.group9.server.Dashboard;

import com.group9.server.HomePage.UserConstants;
import com.group9.server.IExecuteAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.out;

@Component
public class FacultyDashboard implements IDashboard {
    private final String role;
    InputValidator validator;
    IExecuteAction feedback;
    IExecuteAction quiz;
    IExecuteAction manageLecture;
    IExecuteAction viewUserNotifications;
    IExecuteAction facultyAnnouncement;
    IExecuteAction manageMeeting;
    Map<String, IExecuteAction> action = new HashMap<>();
    private String username;

    public FacultyDashboard(InputValidator validator, IExecuteAction viewUserNotificationsImpl, IExecuteAction manageLecture, IExecuteAction facultyAnnouncement, IExecuteAction manageMeeting, IExecuteAction feedback, IExecuteAction quiz) {
        this.validator = validator;
        this.feedback = feedback;
        this.viewUserNotifications = viewUserNotificationsImpl;
        this.manageLecture = manageLecture;
        this.facultyAnnouncement = facultyAnnouncement;
        this.quiz = quiz;
        this.manageMeeting = manageMeeting;
        this.role = UserConstants.FACULTY;
        action.put("1", this.viewUserNotifications);
        action.put("2", this.manageLecture);
        action.put("3", this.facultyAnnouncement);
        action.put("4", this.manageMeeting);
        action.put("5", this.feedback);
        action.put("6", this.quiz);
    }

    @Override
    public void showDashboard() {
        out.println("************************************************");
        out.println("               FACULTY DASHBOARD                ");
        out.println("************************************************");

        out.println("Press 1 --> Notifications");
        out.println("Press 2 --> Manage Lectures");
        out.println("Press 3 --> Send Announcement");
        out.println("Press 4 --> Manage Meetings");
        out.println("Press 5 --> View Feedback");
        out.println("Press 6 --> Add question for a quiz");
        out.println("Press 7 --> Log out");
        out.println("Choose Option:");
        selectMenu();
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    public void selectMenu() {
        Scanner sc = new Scanner(System.in);
        String menuOption = sc.nextLine();
        checkinput(menuOption);
    }

    public void checkinput(String selection) {
        if (this.validator.validate(selection)) {
            IExecuteAction dashboardAction = action.get(selection);
            if (null == dashboardAction) {
                out.println("Logging out...");
                out.println("Logged out successfully...");
                System.exit(0);
            } else {
                dashboardAction.execute(this.role, this.username);
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
